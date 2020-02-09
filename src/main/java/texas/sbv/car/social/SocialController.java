package texas.sbv.car.social;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import texas.sbv.car.proxy.Box;
import texas.sbv.car.proxy.PageProxy;
import texas.sbv.car.user.User;
import texas.sbv.car.user.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class SocialController {
    @Autowired SocialRepository socialRepository;
    @Autowired UserRepository userRepository;
    @Autowired SocialService socialService;
    @Autowired PageProxy pager;
    @Autowired ThumbRepository thumbRepository;
    @Autowired Box box;

    @GetMapping("/viewList/{pageNo}/{userid}")
    public Map<String, Object> viewList(@PathVariable String pageNo, @PathVariable String userid){
        Map<String, Object> map = new HashMap<>();
        pager.setPageNum(Integer.parseInt(pageNo));
        pager.setPageSize(12);
        pager.paging(socialService.allList());
        int thisPageSize = (pager.getEndRow()+1)-((pager.getPageNum()-1)*pager.getPageSize());
        SocialListDTO[] list = new SocialListDTO[thisPageSize];
        List<SocialListDTO> param = socialService.allList();
        for(int i=0; i<thisPageSize; i++){
            list[i]=param.get(pager.getStartRow()+i);
        }
        map.put("boardList", list);
        if(!userid.equals("ghest")){
            map.put("thumbedboard", socialService.thumbed(userid));
        }
        return map;
    }

    @PostMapping("/uploadImg")
    public String uploadImg(MultipartHttpServletRequest uploadFile) {
        Iterator<String> itr =uploadFile.getFileNames();
        String filename = itr.next();
        MultipartFile mfile = uploadFile.getFile(filename);
        String origName=mfile.getOriginalFilename();
        String path = "C:\\Users\\yejee\\IdeaProjects\\TeamRPM\\src\\main\\resources\\static\\img";
        String directory=new SimpleDateFormat("yy-MM-dd").format(new Date()).replace("-", File.separator);
        File serverPath = socialService.makeDir(path, directory);
        serverPath.mkdirs();
        String extension = origName.substring(origName.lastIndexOf(".")+1);
        filename = UUID.randomUUID().toString() +"."+extension;
        File serverFile = socialService.makeFile(serverPath, filename);
        box.add(directory);
        box.add(filename);
        try {
            mfile.transferTo(serverFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "uploadImg";
    }

    @DeleteMapping("/uploadImg")
    public String deleteUploadImg(HttpServletRequest uploadFile){
        /*String origName=uploadFile.getLocalName();
        String extension = origName.substring(origName.lastIndexOf(".")+1);
        //직전에 업로드했던 파일 삭제
        String filePath_ = (String)box.get().get(0);
        Path filePath= Paths.get("C:\\Users\\yejee\\IdeaProjects\\TeamRPM\\src\\main\\resources\\static\\img\\" + filePath_);
        // 어제 업로드 된 첨부파일 목록으로 파일경로 구함
        // ready for check file in directory with DB file list
        List<Path> fileListPaths = fileList
                .stream()
                .map(
                        vo -> Paths.get(
                                "C:\\upload",
                                vo.getUploadPath(),
                                vo.getUuid() + "_" + vo.getFileName())
                )
                .collect(Collectors.toList());


        // 어제 디렉토리 경로
        // files in yesterday directory
        File targetDir = Paths.get("C:\\Users\\yejee\\IdeaProjects\\TeamRPM\\src\\main\\resources\\static\\img\\"+
                (String)box.get().get(0),);

        // 어제 디렉토리와 비교해서 삭제할 파일 경로 구하기
        // attach 테이블에 없는 파일목록 구하기
        File[] removeFiles = targetDir.listFiles(
                file -> fileListPaths.contains(file.toPath()) == false);

        // 하나씩 삭제
        for (File file : removeFiles) {
            file.delete();
        }


*/

        box.clear();
        return "";
    }

    @PostMapping("/writeContent")
    public Boolean writeContent (@RequestBody SocialWriteDTO param){
        socialService.writeContent(param);
        return true;
    }

    @GetMapping("/loadBoard/{boardSeq}")
    public SocialDetailDTO loadBoard(@PathVariable String boardSeq){
        return socialService.loadBoard(boardSeq);
    }

    @PostMapping("/updateContent/{boardSeq}")
    public Boolean updateContent (@PathVariable String boardSeq, @RequestBody SocialWriteDTO socialWriteDto){
        socialService.updateContent(boardSeq, socialWriteDto);
        return true;
    }
    @GetMapping("/deleteContent/{boardSeq}")
    public Boolean deleteContent(@PathVariable String boardSeq){
        socialService.deleteContent(boardSeq);
        return true;
    }

    @GetMapping("/thumbUp/{boardSeq}/{userid}")
    public Boolean thumbUp(@PathVariable String boardSeq, @PathVariable String userid){
        socialService.thumbUp(boardSeq, userid);
        return true;
    }
    @GetMapping("/thumbDown/{boardSeq}/{userid}")
    public Boolean thumbDown(@PathVariable String boardSeq, @PathVariable String userid){
        socialService.thumbDown(boardSeq, userid);
        return true;
    }

    @GetMapping("/thumbed/{boardSeq}/{userid}")
    public Boolean thumbed(@PathVariable String boardSeq, @PathVariable String userid){
        Boolean result = false;
        Social social = socialRepository.findById(Long.parseLong(boardSeq)).get();
        User user = userRepository.findByUserid(userid);
        Thumb thumb = thumbRepository.findByBoardSeqAndUserSeq(social, user);
        if(thumb != null){result = true;}
        return result;
    }

}
