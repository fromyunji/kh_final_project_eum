<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이음 :: 글쓰기</title>
<style>
.tab_title li {
	display: inline-block;
	padding: 1em 2em;
	/* margin: 0.5em; */
	cursor: pointer;
	color: #333;
	border-radius: 0.25em;
	background: #fff;
	min-width: 74px;
	height: 40px;
	line-height: 40px;
	padding: 0 14px 0;
	border-radius: 4px;
	text-align: center;
	color: #555;
	font-size: 14px;
	box-sizing: border-box;
	border: 1px solid #e5e5e5;
	background: none;
}

.tab_title li.on {
	background: #3865f2;
	color: #fff;
	font-weight: bold;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.2), inset 1px 1px 3px 2px #2855da;
	;
	/* box-shadow: inset 1px 1px 15px 2px #3865f2; */
}

.tab_cont {
	/* clear: both; */
	
}

.write-frm {
	display: none;
}

.tab_cont div.on {
	display: block;
}

.tab-nav {
	/* clear: both; */
	
}

.comm-write-tab-nav {
	display: flex;
}

.comm-input {
	position: relative;
	font-size: 14px;
	line-height: 20px;
	padding: 0 16px;
	height: 48px;
	background-color: #fff;
	border: 1px solid #d6d6e7;
	border-radius: 3px;
	color: rgb(35, 38, 59);
	box-shadow: inset 0 1px 4px 0 rgb(119 122 175/ 30%);
	overflow: hidden;
	transition: all 100ms ease-in-out;
	width: 80%;
}

.comm-input:focus {
	outline: none;
	border: 1px solid #3865f2;
	box-shadow: 0 1px 0 0 rgb(35 38 59/ 5%);
}

.commFileBox label {
	display: inline-block;
	padding: .5em .75em;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	cursor: pointer;
	border: 1px solid #1abc9c;
	border-radius: .25em;
	color: #fff;
	background-color: #1abc9c;
	margin-bottom: 20px;
}

.commFileBox input[type="file"] { /* 파일 필드 숨기기 */
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

.filebox .upload-display { /* 이미지가 표시될 지역 */
	margin-bottom: 5px;
}

@media ( min-width : 768px) {
	.filebox .upload-display {
		display: inline-block;
		margin-right: 5px;
		margin-bottom: 0;
	}
}

.filebox .upload-thumb-wrap { /* 추가될 이미지를 감싸는 요소 */
	display: inline-block;
	width: 54px;
	padding: 2px;
	vertical-align: middle;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #fff;
}

.filebox .upload-display img { /* 추가될 이미지 */
	display: block;
	max-width: 100%;
	width: 100% \9;
	height: auto;
}

.input-img-box {
	padding: 20px;
}

.img-box-wrap {
	display: flex;
	min-height: 222px;
}

.write-frm-help {
	background-color: #f9f9fa;
	border: 1px solid #ebecef;
	color: #323232;
	padding: 20px;
	margin-bottom: 30px;
	border-radius: 6px;
}

.optButton {
	border: none;
	background: none;
}

.addOptBtn>i, .addOptBtn2>i {
	color: #1abc9c;
	font-size: 28px;
	vertical-align: top;
	padding-top: 0px;
}

.comm-write-p {
	font-size: 18px;
	margin-top: 45px;
	margin-bottom: 10px;
}

.optBox {
	margin-top: 15px;
}

.delOptBtn>i, .delOptBtn2>i {
	font-size: 33px;
	vertical-align: middle;
}
</style>
</head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<body>
	<div class="container">
		<div class="comm-write-tab-nav">
			<ul class="tab_title">
				<li class="on">요즘뭐하니?</li>
				<li>이거어때?!</li>
			</ul>
		</div>

		<div class="article-wrap">
			<div class="tab_cont">
				<div class="write-frm on">
					<form action="/communityWrite.do" method="post">
						<p class="comm-write-p">제목</p>
						<input type="text" name="commTitle" class="commTitle comm-input"
							placeholder="제목을 입력하세요">
						<p class="comm-write-p">간단한 소개</p>
						<input type="text" name="commIntro" class="commIntro comm-input"
							placeholder="간단한 소개">
						<!-- 대표이미지 -->
						<div class="img-box-wrap">
							<div class="commFileBox preview-image">
								<p class="comm-write-p" style="margin-bottom: 5px;">대표이미지</p>
								<h4 class="fc-3" style="margin-top: 0;">(세로로 긴 사진이 적합합니다.)</h4>
								<label for="input-file">파일 선택</label> <input type="file"
									name="commFilename" class="commFile upload-hidden"
									id="input-file">
							</div>
							<div class="input-img-box"></div>
						</div>

						<!--장점/단점-->
						<div class="write-frm-help">
							<i class="fa-solid fa-circle-question fc-9"></i> <span
								class="title-text fs-medium fc-9">추천과 비추천</span>
							<p>추천하는 이유: 내가 소개하는것과 어울리는 사람, 좋은점 등을 적어주세요.</p>
							<p>비추천하는 이유: 내가 소개하는것과 어울리지 않는 사람, 단점 등을 적어주세요.</p>
						</div>
						<div class="adv-input">
							<span class="comm-write-p"> 추천하는 이유
								<button type="button" class="addOptBtn optButton">
									<i class="fa-solid fa-square-plus"></i>
								</button>
							</span>
							<h4 class="fc-3" style="margin-top: 10px;">(최대 5개까지 추가 가능)</h4>
							<div id="adv-opt">
								<input type="text" name="advantage" class="comm-input"
									placeholder="내용을 입력하세요.">
							</div>
						</div>
						<div class="weak-input" style="margin-top: 50px;">
							<span class="comm-write-p"> 추천하지 않는 이유
								<button type="button" class="addOptBtn2 optButton">
									<i class="fa-solid fa-square-plus"></i>
								</button>
							</span>
							<h4 class="fc-3" style="margin-top: 10px;">(최대 5개까지 추가 가능)</h4>
							<div id="weak-opt">
								<input type="text" name="advantage" class="comm-input"
									placeholder="내용을 입력하세요.">
							</div>
						</div>


						<!--썸머노트-->
						<p class="comm-write-p">상세 소개</p>
						<div class="pt-1" style="margin-bottom: 50px;">
							<textarea id="summernote" name="editordata"></textarea>
						</div>
						<input type="submit" class="btn bc1 bs4" style="margin: 30px 0;"
							value="등록하기"></input>
					</form>
				</div>
			</div>
			<!--tab_cont end-->
		</div>
		<!--article-wrap end-->
	</div>
	<!--container end-->


<!-- script -->
<script>
        $(document).ready(function() {
            $(".tab_title li").click(function() {
                var idx = $(this).index();
                $(".tab_title li").removeClass("on");
                $(".tab_title li").eq(idx).addClass("on");
                $(".tab_cont > div").hide();
                $(".tab_cont > div").eq(idx).show();
            })
             
            //썸머노트 불러오기 함수 
            $("#summernote").summernote({
            	  toolbar: [ //썸머노트 툴바 추가
      			    ['fontsize', ['fontsize']],
      			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
      			    ['color', ['forecolor','color']],
      			    ['table', ['table']],
      			    ['para', ['ul', 'ol', 'paragraph']],
      			    ['height', ['height']],
      			    ['insert',['picture','link','video']],
      			    ['view', ['fullscreen', 'help']]
      			 	 ],
      			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			  height: 500,                 // 에디터 높이
			  minHeight: null,             // 최소 높이
			  maxHeight: null,             // 최대 높이
			  focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
			  lang: "ko-KR",					// 한글 설정
			  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
          
			});
            
            
            // 이미지 추가 함수 시작
            var imgTarget = $('.preview-image .upload-hidden');

            imgTarget.on('change', function(){
              var parent = $(this).parent().next();
              parent.children('.upload-display').remove();

                if(window.FileReader){
                    //image 파일만
                    if (!$(this)[0].files[0].type.match(/image\//)) return;
                    
                    var reader = new FileReader();
                    reader.onload = function(e){
                        var src = e.target.result;
                        parent.append('<div class="upload-display" style="display:inline-block; border: 1px solid #eee; padding: 10px;"><div class="upload-thumb-wrap"><img src="'+src+'" class="upload-thumb" style="height: 160px;"></div></div>');
                    }
                    reader.readAsDataURL($(this)[0].files[0]);
                }

                else {
                    $(this)[0].select();
                    $(this)[0].blur();
                    var imgSrc = document.selection.createRange().text;
                    parent.prepend('<div class="upload-display" style="border: 1px solid #eee; padding: 10px;"><div class="upload-thumb-wrap"><img class="upload-thumb" style="width: 120px;"></div></div>');

                    var img = $(this).siblings('.upload-display').find('img');
                    img[0].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""+imgSrc+"\")";        
                }
            });// 이미지 추가 함수 종료
          

          
          //옵션 추가 버튼함수 시작
                var advCount = 1;
                var weakCount = 1;
           
            $(".addOptBtn").on("click",function(){
                if(advCount >= 5) return;
                var advDiv = document.createElement("div");
                advDiv.setAttribute("class","optBox");
                advDiv.innerHTML = '<input type="text" name="advantage" class="comm-input" placeholder="내용을 입력하세요."><button class="delOptBtn optButton"><i class="fa-solid fa-square-minus fc-9"></i></button>';
                $("#adv-opt").append(advDiv);
                advCount++;
                $(".delOptBtn").off().on("click",function(){
                      $(this).parent().remove();
                      advCount--;
                });
            });

            $(".addOptBtn2").on("click",function(){
                if(weakCount >= 5) return;
                var newDiv = document.createElement("div");
                newDiv.setAttribute("class","optBox");
                newDiv.innerHTML = '<input type="text" name="weakness" class="comm-input" placeholder="내용을 입력하세요."><button class="delOptBtn2 optButton"><i class="fa-solid fa-square-minus fc-9"></i></button>';
                $("#weak-opt").append(newDiv);
                weakCount++;
                $(".delOptBtn2").off().on("click",function(){
                      $(this).parent().remove();
                      weakCount--;
                });
            });

        });
  </script>
  </body>
  <%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>