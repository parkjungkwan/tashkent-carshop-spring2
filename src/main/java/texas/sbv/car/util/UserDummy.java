package texas.sbv.car.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import texas.sbv.car.user.User;



public class UserDummy{

    public User makeUser() {
        String name = "", userId = "", passwd = "", email = "", gender = "", region = "";
        User user = new User();
        List<String> LN = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> FN = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        List<String> genders = Arrays.asList("man", "woman");
        List<String> en1 = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
                "p", "q", "r", "s", "t", "u", "v", "x", "y", "z");
        List<String> emails = Arrays.asList("daum.net","naver.com","nate.com","dreamwiz.com","empal.com","freechal.com",
                "gmail.com","hanafos.com","hanmir.com","hitel.net","hotmail.com","korea.com","lycos.co.kr","netian.com",
                "paran.com","yahoo.co.kr","chollian.net","hanmail.net","kebi.com");
        List<String> regions = Arrays.asList("서울","인천","대전","대구","광주","부산","울산","세종","경기","강원","경남","경북",
                "전남","전북","충남","충북","제주");
        Collections.shuffle(LN);
        Collections.shuffle(FN);
        Collections.shuffle(genders);
        Collections.shuffle(emails);
        Collections.shuffle(regions);
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(en1);
            userId += en1.get(0);
        }
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(en1);
            passwd += en1.get(0);
        }
        name = LN.get(0)+FN.get(0)+FN.get(1);
        gender = genders.get(0);
        email = userId + "@"+emails.get(0);
        region = regions.get(0);
        user.setUserid(userId);
        user.setPasswd(passwd);
        user.setName(name);
        user.setGender(gender);
        user.setEmail(email);
        user.setRegion(region);
        user.setAuth(0);
        user.setBirthMonth(setBirth());
        return user;
    }
    public String setBirth(){
        Random r = new Random();
        String y,m= "";
        y=String.valueOf(r.nextInt(110)+1909);
        List<String> month= Arrays.asList("01","02","03","04","05","06","07","08","09","10","11","12");
        Collections.shuffle(month);
        m= month.get(0);
        return y+m;
    }


}