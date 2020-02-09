package texas.sbv.car.social;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import texas.sbv.car.car.CarRepository;
import texas.sbv.car.proxy.Box;
import texas.sbv.car.user.User;
import texas.sbv.car.user.UserRepository;

@Service
@Lazy
public class SocialServiceImpl implements SocialService{

    @Autowired SocialRepository socialRepository;
    @Autowired CarRepository carsRepository;
    @Autowired UserRepository userRepository;
    @Autowired Social social;
    @Autowired SocialDetailDTO socialDetailDto;
    @Autowired User user;
    @Autowired ThumbRepository thumbRepository;
    @Autowired Thumb thumb;
    @Autowired Box box;


    @Transactional(readOnly = true)
    @Override
    public List<SocialListDTO> allList() {
        List<Social> list = (List<Social>) socialRepository.findAll();
        List<SocialListDTO> lists =  list.stream()
                .map(social -> new SocialListDTO(
                        social.getBoardSeq(),
                        social.getBoardDate(),
                        social.getCarCode(),
                        social.getCarName(),
                        social.getBoardContent(),
                        social.getBoardImg(),
                        social.getUserSeq().getName(),
                        social.getThumbs().size()))
                .sorted(Comparator.comparing(SocialListDTO::getBoardSeq).reversed())
                .collect(Collectors.toList());
        return lists;
    }


    @Override
    public String[] thumbed(String userid) {
        List<Thumb> thumbList = (List<Thumb>)thumbRepository.findAll();
        List<Thumb> thumbsByUserid =thumbList.stream().filter(t->t.getUserSeq().getUserid().equals(userid))
                .collect(Collectors.toList());
        String[] thumbArray = new String[thumbsByUserid.size()];
        for(int i = 0; i<thumbsByUserid.size(); i++){
            thumbArray[i]= String.valueOf(thumbsByUserid.get(i).getBoardSeq().getBoardSeq());
        }
        return thumbArray;
    }


    @Override
    public SocialDetailDTO loadBoard(String boardSeq){
        social = (socialRepository.findById(Long.parseLong(boardSeq))).get();
        socialDetailDto = new SocialDetailDTO();
        socialDetailDto.setCarName(social.getCarName());
        socialDetailDto.setBoardContent(social.getBoardContent());
        socialDetailDto.setBoardDate(social.getBoardDate());
        socialDetailDto.setBoardImg(social.getBoardImg());
        socialDetailDto.setUserid(social.getUserSeq().getUserid());
        socialDetailDto.setUserName(social.getUserSeq().getName());
        //socialDetailDto.setCommentCount(social.getComments().size());
        socialDetailDto.setThumbCount(social.getThumbs().size());
        return socialDetailDto;
    }

    @Override
    public void writeContent(SocialWriteDTO param){
        user = userRepository.findByUserid(param.getUserid());
        social = new Social();
        social.setUserSeq(user);
        social.setCarName(param.getCarName());
        social.setBoardDate(new SimpleDateFormat ( "yy.MM.dd HH:mm:ss").format( new Date()));
        social.setCarCode("board"+social.getBoardDate());
        int boxSize=box.get().size();
        social.setBoardImg("img"+File.separator
                +box.get().get(boxSize-2)+File.separator
                +box.get().get(boxSize-1));
        box.clear();
        social.setBoardContent(param.getBoardContent());
        socialRepository.save(social);
    }

    @Override
    public File makeDir(String t, String u) {
        BiFunction<String,String,File> f = File :: new;
        return f.apply(t, u);
    }

    @Override
    public File makeFile(File t, String u) {
        BiFunction<File, String, File> f = File :: new;
        return f.apply(t, u);
    }


    @Override
    public void updateContent(String boardSeq, SocialWriteDTO socialWriteDto){
        social = socialRepository.findById(Long.parseLong(boardSeq)).get();
        if(socialWriteDto.getBoardImgName()=="oldImg"){
            social.setBoardImg(social.getBoardImg());
        }else{
            social.setBoardImg("img"+File.separator
                    +box.get().get(0)+File.separator
                    +box.get().get(1));
        }
        social.setCarName(socialWriteDto.getCarName());
        social.setBoardContent(socialWriteDto.getBoardContent());
        socialRepository.save(social);
    }

    @Override
    public void deleteContent(String boardSeq) {
        social = socialRepository.findById(Long.parseLong(boardSeq)).get();
        socialRepository.delete(social);
    }

    @Override
    public void thumbUp(String boardSeq, String userid) {
        thumb.setBoardSeq(socialRepository.findById(Long.parseLong(boardSeq)).get());
        thumb.setUserSeq(userRepository.findByUserid(userid));
        thumbRepository.save(thumb);
    }

    @Override
    public void thumbDown(String boardSeq, String userid) {
        social = socialRepository.findById(Long.parseLong(boardSeq)).get();
        user = userRepository.findByUserid(userid);
        thumb = thumbRepository.findByBoardSeqAndUserSeq(social, user);
        thumbRepository.delete(thumb);
    }
}
