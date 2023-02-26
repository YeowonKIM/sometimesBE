# Sometimes BE
<br/> 

#### | 📢 Project Planning |
  • 프로젝트 이름 : ㄱr끔 <br/> 
  • 프로젝트 설명 : 익명 소통 공간 (모씨 오마주)  <br/> 
  • 팀원 이름 : 이승렬(팀장), 김여원, 함동진, 
    

<br/> <br/> 

## 🔗 STEP 1 : 서비스 요구사항 


 #### 1. 회원 가입 API

    • username, nickname, password를 Client에서 전달받기

    • username은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 한다.
    
    • nickname은 최소 2자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 한다.

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
  
  
  ## 🧱 STEP 2 :  ERD
![Sometimes (2)](https://user-images.githubusercontent.com/122272525/221391247-973cf25c-17e1-47f6-aba9-d6f0dd45d3ba.png)

<br/><br/>


## 🏗️ STEP 3 :   API Specification
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcqGctI%2Fbtr0KRV9lbR%2Fv73RF8TvSNo2JsANPSAkJ0%2Fimg.png"  width="800" height="400">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbCOAgh%2Fbtr0RPpVDf5%2FM7ss6l02aiuVm7k6uYTS10%2Fimg.png"  width="800" height="600">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbHBwgA%2Fbtr0GbhiFKu%2FFKbEbGhKT2jjgBZRCkOg4K%2Fimg.png"  width="800" height="600">



<br/><br/>


  ## 💡 STEP 4 : Core Tools
  <img src="https://img.shields.io/badge/Spring-green?style=for-the-badge&logo=Spring&logoColor=#6DB33F"> <img src="https://img.shields.io/badge/Spring Boot-green?style=for-the-badge&logo=Spring Boot&logoColor=#6DB33F"> <img src="https://img.shields.io/badge/Spring Security-green?style=for-the-badge&logo=Spring Security&logoColor=#6DB33F">

<br/><br/>
 
   ## 📌 STEP 5 : Trouble Shooting


<br/><br/>
  
