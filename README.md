### 아주대학교 모바일 프로그래밍 수업 최종 프로젝트

##### Kinder Book 프로젝트

##### 프로젝트 주제 선정 배경
###### 주제 선정 이유
- 현직 유치원 교사의 인터뷰를 통해 유치원 교사가 퇴근 시간 이후 학부모의 연락, 급식 알레르기에 대한 정보를 하나하나 완벽히 인지하고 있어야한다는 것에 스트레스를 받는다는 것을 알게 되었다.
- 국가에서 유치원생을 대상으로 관리해주는 서비스가 존재하지 않다. (초등학교 안내 서비스 e알리미와 같은 서비스가 존재하나 e알리미는 학교 혹은 교육청에서 가정과 학교로 일방향 공지만 가능하다.
- 학부모들이 현재 자신의 자녀가 유치원에서 어떤 활동을 하고 무엇을 먹고 있는지 궁금해하는 경우가 많다.
##### 어플리케이션 구현
###### 데이터베이스
<img src='/mobile_db.png'></img>

##### Android
###### Server Packages
모델 객체와 RetrofitClient, RetrofitInterface가 포함된다. 모델 객체는 필드, 생성자, getter & setter, toString으로 구성되어있다. Gson 라이브러리의 SerializedName 어노테이션은 모델 객체의 필드를 Json 형식으로 직렬화 / 역직렬화 할 때 사용되고 Expose 어노테이션은 객체가 null 값일 경우 Json으로 만들 필드를 자동 생략해준다.
-  Model
  1. Board
     postName에는 알림장 포스트의 제목, postBody에는 알림장 포스트의 내용이 저장된다.
  2. ClassName
     className에 반 이름이 저장된다.
  3. ClassroomList
     classId에는 반 번호, className에는 반 이름, tName에는 담임 교사 이름이 저장된다.
  4. Kids
     kidsName에는 원생 이름, classId에는 원생이 속해있는 반 번호가 저장된다.
-  RetrofitClient
Retrofit 객체를 static으로 생성하여 하나의 객체로 운영하기 위하여 싱글톤 패턴을 적용하였다. 생성자에서 String 타입의 URL과 Json을 다룰 수 있는 컨버터를 주입해준다. 이 객체는 getInstance 메소드를 통해 호출할 수 있다. Retrofit 인터페이스를 호출할 수 있는 getRetrofitInterface 메소드가 있다. 호출한 인터페이스에서 필요한 메서드를 선택하고 매개변수를 삽입한 뒤에 콜백 함수 형태로 데이터를 주고받는다. 데이터를 조회한다면 데이터베이스로부터 Response.body에 객체가 실려 온다. 통신이 성공했을 때는 onResponse 메서드 안에서 성공하였다는 Log를 띄우고 데이터를 받아온다. 실패했을 경우에는 onFailure 메서드에서 에러 Log를 띄우고 어떤 오류가 발생하였는지 Throwable t를 메세지로 받아 확인할 수 있다.
-  RetrofitInterface
백엔드로 구현한 REST API를 이용할 수 있게 URL과 매핑하는 인터페이스다. 실제 클라이언트에서 필요한 메소드를 호출하여 사용하는 방식이다. 데이터베이스에서 조회할 수 있는 GET 방식, 삽입할 수 있는 PUT 방식, 삭제할 수 있는 DELETE 방식을 어노테이션을 사용하여 명시하고, 어노테이션 옆에 URL을 입력한다. 매개변수로 데이터를 주고받을 수 있는 방법은 여러가지가 있는데, 본 프로젝트에서는 간단히 두 가지 방식을 사용한다. Path 어노테이션은 URL에 매개변수를 직접 삽입하는 방식으로 사용되고, Query 어노테이션은 정해진 URL로 모든 데이터를 전달하고 받을 수 있다.
###### Activity package
