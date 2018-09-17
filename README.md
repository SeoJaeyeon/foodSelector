# 오늘 뭐먹지?!

**프로젝트 개요**

매일 겪는 외식 메뉴 선정에 대한 어려움을 해결하기 위해 위치 기반의 음식점 중 사용자의 선호도를 고려한 장소를 랜덤으로 선택해주는 안드로이드 기반의 모바일 애플리케이션 



**프로젝트 구성원**

졸업프로젝트로 4명의 구성원 중 Spring Frame기반의 Back-end 개발 담당



**시스템 구조**

![system-architecture](https://github.com/SeoJaeyeon/foodSelector/blob/master/img/system-architecture.png?raw=true)



**주요요구사항 및 구현내용**

- 사용자의 위치를 기반으로 음식점을 출력

: 카카오 로컬 API를 통해 위치 기반의 음식점 목록을 요청하고 파싱하여 클라이언트에 전달

- 사용자 커스텀 목록 제공

: DB에 커스텀 테이블을 구축하여 사용자가 음식점을 추가, 삭제할 수 있도록 하고 커스텀 카테고리 또한 추가, 삭제 할 수 있도록 함



**시연영상(음성제거이후로 약간 끊기는 점 양해부탁드립니다.)**

  [![Video Label](http://img.youtube.com/vi/uLR1RNqJ1Mw/0.jpg)](https://youtu.be/BYCYYtDsTN0)

