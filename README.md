# Sometimes BE
<br/> 

## 🔗 STEP1. 프로젝트 소개

---

### "ㄱr끔" 은 추억의 익명 소통 공간입니다.

#### 옛날 느낌 그대로를 느껴보고 싶지 않으신가요?
   - 그때 그 시절 소통공간 그대로 다른사람들에게 익명으로 자신의 생각을 전달해보세요!

#### 다른사람들의 글들이 궁금하다면?
   - 다른사람들의 글을 읽어보고 좋아요를 눌러보아요!

<br/><br/>


##  🛠️ STEP2. 프로젝트 기능 명세서

 #### 1. 회원 가입 API

    • username, nickname, password를 Client에서 전달받기

    • username은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 한다.
    
    • nickname은 최소 2자 이상, 10자 이하로 구성되어야 한다.

    • password는 최소 8자 이상, 24자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 한다.

    • DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환한다.
    
    • 회원 권한 부여하기 (ADMIN, USER) - ADMIN 회원은 모든 카드 수정/삭제 가능
    


  #### 2. 로그인 API

    • username, password를 Client에서 전달받기

    • DB에서 username을 사용하여 저장된 회원의 유무를 확인하고 있다면 password 비교하기

    • 로그인 성공 시, 로그인에 성공한 유저의 정보와 JWT를 활용하여 토큰을 발급하고,

      발급한 토큰을 Header에 추가하고 성공했다는 메시지, 상태코드 와 함께 Client에 반환하기
      
      
  #### 3. 익명 카드 작성 API 

    • Spring Security를 사용하여 토큰 검사 및 인증하기 : 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 카드만 등록 가능

    • nickname, 작성 내용을 저장하고 저장된 게시글을 Client 로 반환하기
    
    
 #### 4. 카드 전체 조회 API

    • nickname, 작성 내용, 작성일시, 수정일시를 조회하기

    • 작성 날짜 기준 내림차순으로 정렬하기

    • 카드에 '좋아요' 개수도 함께 반환하기
    
 
  #### 4. 선택한 카드 조회 API
  
    • 선텍한 카드의 nickname, 작성 내용, 작성일시, 수정일시를 조회하기

    • 작성 날짜 기준 내림차순으로 정렬하기

    • 카드에 '좋아요' 개수도 함께 반환하기

 

   #### 5. 선택한 카드 수정 API 

      • Spring Security를 사용하여 토큰 검사 및 인증하기 : 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 카드만 수저 가능
      
      • 제목, 작성 내용을 수정하고 수정된 카드를 Client 로 반환하기
      
      • 카드에 '좋아요' 개수도 함께 반환하기

 

   #### 6. 선택한 카드 삭제 API

      • Spring Security를 사용하여 토큰 검사 및 인증하기 : 토큰을 검사한 후, 유효한 토큰이면서 해당 사용자가 작성한 카드만 삭제 가능

      • 선택한 게시글을 삭제하고 Client 로 성공했다는 메시지, 상태코드 반환하기
  <br/><br/>
  
  
  ## 🧱 STEP 3 :  ERD
  ![Sometimes (1)](https://user-images.githubusercontent.com/122272525/222137280-9d26218f-0934-4b52-81ee-9435a5f8ef69.png)


<br/><br/>


## 🏗️ STEP 4 :   API Specification
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FLKawc%2Fbtr1lxcsBQU%2FRkeYSIHkLch1kyUyXctFyk%2Fimg.png"  width="950" height="430">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FxDhK0%2Fbtr1f1E8jQI%2FSymF41H6hJA4inufPBQ9iK%2Fimg.png"  width="980" height=750">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FrDpL7%2Fbtr1lxcupSW%2FubJJdk4q2ezejrUqi8x1k1%2Fimg.png"  width="950" height="700">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FYpnvi%2Fbtr1lA1waPK%2F2IXgWoS14n7cdsPlUklFAK%2Fimg.png"  width="900" height="200">



<br/><br/>


  ## 💡 STEP 5 : Core Tools
<img src="https://img.shields.io/badge/Spring-green?style=for-the-badge&logo=Spring&logoColor=#6DB33F"> <img src="https://img.shields.io/badge/Spring Boot-green?style=for-the-badge&logo=Spring Boot&logoColor=#6DB33F"> <img src="https://img.shields.io/badge/Spring Security-green?style=for-the-badge&logo=Spring Security&logoColor=#6DB33F">
<br/>
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>  <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white"/> <img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white"/>




<br/><br/>
 
   ## 📌 STEP 6 : Trouble Shooting
| 트러블 슈팅 내용 | 해결 방법 |
| --- | --- |
| 카드 삭제 부분, 글쓴이의 아이디와 실제 유저 아이디 비교 | `equals`  `==` |
| 로그인 시 토큰 발급 안되는 증상 발생 | HTTP Header 부분이 제대로 return이 안 되어 있었음. |
| 좋아요 갯수 → 많아졌을 때 | scheduler 로 갱신하는 방법을 찾아보기. → 동시성 |
| AccessToken이 탈취되었을때의 문제점 및 대처 | refreshToken. <br> [https://hudi.blog/refresh-token/](https://hudi.blog/refresh-token/) <br> [https://tecoble.techcourse.co.kr/post/2021-10-20-refresh-token/](https://tecoble.techcourse.co.kr/post/2021-10-20-refresh-token/) <br> [https://velog.io/@jkijki12/Jwt-Refresh-Token-적용기](https://velog.io/@jkijki12/Jwt-Refresh-Token-%EC%A0%81%EC%9A%A9%EA%B8%B0) |
| 중복된 이미지를 사용하는 경우, 혹은 삭제된 아이디의 이미지를 삭제하려는 경우 문제됨. | Card를 삭제할 경우, LikeRepository에서 이미지가 삭제 되는 것 수정. <br> ➡️ oneToOne이기 때문에 이미지를 DB에서 삭제하지 않는 방향으로 수정 |
| IntelliJ MySQL 연결 | Host 부분 URL, Database 부분 이름 |
| Optional 잘 쓰는 법. | [https://mangkyu.tistory.com/70](https://mangkyu.tistory.com/70) <br> [https://hbase.tistory.com/212](https://hbase.tistory.com/212) <br> [https://coding-factory.tistory.com/547](https://coding-factory.tistory.com/547) |
| content (text 와 blob 의 차이점.) // 만약 이모지도 넣고싶다면? (이모지가 얼마나 차지를 하냐) |  |
| `httpSecurity.csrf().disable();` |  |
| `@AuthenticationPrincipal UserDetailsImpl userDetails` |  |


<br/><br/>

## 📌 STEP 7 : 팀원 정보 및 팀 노션

| 이름 | 깃허브 주소 |
| --- | --- |
| 이승렬 | https://github.com/LEESEUNGRYEOL |
| 김여원 | https://github.com/YeowonKIM |
| 함동진 | https://github.com/eastjin |
| 황원준 | https://github.com/1juuun |

#### TEAM NOTION : https://expensive-butterfly-598.notion.site/1-SA-3b52011bd6424c288893bdda6d543280
