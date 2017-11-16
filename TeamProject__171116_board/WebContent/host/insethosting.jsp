<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% String str = "부산광역시 동래구 안락2동 180-26"; %>
<script src ="host/jquery-3.2.1.js"></script>
<script>
      // This example displays a marker at the center of Australia.
      // When the user clicks the marker, an info window opens.
      function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        alert(address);
        geocoder.geocode({'address': address}, function(results, status) {
          if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            //alert(results[0].geometry.location);
            var marker = new google.maps.Marker({
              map: resultsMap,
              position : results[0].geometry.location,
            });
          } else {
            alert('주소를 찾을수없습니다. 다시입력해 주세요 ');
          }
        });
      }
      function initMap() {
        var busan = {lat: 35.1578157, lng: 129.0600331};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 16,
          center: busan
        });
        var geocoder = new google.maps.Geocoder();
        
        document.getElementById('upmap').addEventListener('click', function() {
            geocodeAddress(geocoder, map);
        });
      }
      function test()
      {
       var fileName = new Array("File1", "File2", "File3", "File4", "File5");

       for (var i=0; i<fileName.length-1; i++)
       {
           if (document.getElementById(fileName[i]).value == "")
                continue;
            
        for (var j=i+1; j<fileName.length; j++)
        {
            if (document.getElementById(fileName[j]).value == "")
                continue;
                
         if (document.getElementById(fileName[i]).value == document.getElementById(fileName[j]).value)
         {
          alert("중복");
          return false;
         }
        }
       }
       alert("중복없음");
       return true;
      }

</script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key= AIzaSyAM56gRYD0iGeLl1iWXFpAuiqiWM9BBK7w&callback=initMap"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        width:50%;
        height: 600px;
        margin: 0 auto;
      }
      #head{
      width: 100%;
      height: 70px;
      background-color: red;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #floating-panel {
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
        display: flex;
      }

 
    </style>
  <script src ="jquery-3.2.1.js"></script>
</head>
<body>
<div id ="head"></div>

<div id="container">
<form action="./HostinginsetAction.bo" enctype="multipart/form-data" method="post">
간단소개 : <input type="text" name="subject"><br>
상세소개 : <textarea rows="15" cols="100" name = "content"></textarea><br>
<h1>option : </h1>
인원 : 
<select>
<option>1인</option>
<option>2인</option>
<option>4인</option>
<option>8인</option>
</select>
침실 : 
<select>
<option>1개</option>
<option>2개</option>
<option>3개</option>
<option>4개</option>
</select>
침대 : 
<select>
<option>1개</option>
<option>2개</option>
<option>3개</option>
<option>4개</option>
</select>
욕실 : 
<select>
<option>1개</option>
<option>2개</option>
<option>3개</option>
<option>4개</option>
</select>
<br>
<h1>편의 시설:</h1>
<table>
<tr>
<td>반려동물 입실 가능 :</td><td> <input type="checkbox"></td><td>엘리베이터 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>가족/어린이 숙박에 적합 :</td><td><input type="checkbox"></td><td>다리미 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>흡연 가능 :</td><td><input type="checkbox"></td><td>인터넷 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>수영장 :</td><td><input type="checkbox"></td><td>필수품목 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>헬스장 :</td><td><input type="checkbox"></td><td>무선 인터넷 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>아침식자 :</td><td><input type="checkbox"></td><td>헤어드라이어 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>자쿠지 욕조 :</td><td><input type="checkbox"></td><td>세탁기 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>건물 내 무료 주차 :</td><td><input type="checkbox"></td><td>샴푸 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>건조기 :</td><td><input type="checkbox"></td><td>옷걸이 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>휠체어 접근 가능 :</td><td><input type="checkbox"></td><td>에어컨 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>실내 벽난로 :</td><td><input type="checkbox"></td><td>TV :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>이벤트/행사가능 :</td><td><input type="checkbox"></td><td>난방 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>경비원 :</td><td><input type="checkbox"></td><td>부엌 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>초인종/인터폰 :</td><td><input type="checkbox"></td><td>케이블 TV :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>노트북 작업 공간 :</td><td><input type="checkbox"></td><td>게스트 전용 출입문 :</td><td> <input type="checkbox"></td>
</tr>
</table>
<h1>가족 편의시설</h1>
<table>
<tr>
<td>아기 욕조 :</td><td> <input type="checkbox"></td><td>게임기 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>아기 모니터 :</td><td><input type="checkbox"></td><td>유아 식사용 의자 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>베이비시터 추천 가능 :</td><td><input type="checkbox"></td><td>전원 콘센트 덮개 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>욕조 :</td><td><input type="checkbox"></td><td>다기능 / 여행용 아기 침대 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>기저귀 교환대 :</td><td><input type="checkbox"></td><td>암막 커튼 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>어린이용 책과 장난감 :</td><td><input type="checkbox"></td><td>계단 차단문 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>어린이용 식기 :</td><td><input type="checkbox"></td><td>테이블 모서리 보호대 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>아기 침대 :</td><td><input type="checkbox"></td><td>창문 안전장치 설치 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>벽난로 안전장치 :</td><td><input type="checkbox"></td><td></td><td></td>
</tr>
</table>
<h1>안전 기능</h1>
<table>
<tr>
<td>화재 감지기 :</td><td> <input type="checkbox"></td><td>일산화탄소 감지기 :</td><td> <input type="checkbox"></td>
</tr>
<tr>
<td>소화기 :</td><td><input type="checkbox"></td><td></td><td></td>
</tr>

</table><br>
가격 : <input type="text" name="price"><br>
주소 : <input type="text" name="address" id="address"><input type="button" id = "upmap" value="위치 확인"><br>
<table>
<tr>
<td>사진 : </td><td><input type="file" id="File1" name="File1"></td>
</tr>
<tr>
<td></td><td><input type="file" id="File2" name="File2"></td>
</tr>
<tr>
<td></td><td><input type="file" id="File3" name="File3"></td>
</tr>
<tr>
<td></td><td><input type="file" id="File4" name="File4"></td>
</tr>
<tr>
<td></td><td><input type="file" id="File5" name="File5"></td>
</tr>


</table>

<div id="map"></div>
<input type="submit" value="호스팅하기" onclick="test()">
<input type="reset" value="다시쓰기">
</form>
</div>



</body>
</html>