//Create by WWQ.
var CommonData={};
CommonData.elistId = null;


function ajax(type, url, data, suc, fail) {
	$.ajax({
		type : type,
		url : url,
		data : data,
		dataType : "text",
		success : suc,
		error : fail
	});

}

function setInformation(id, data) {
	var idName = "#" + id, element = $(idName);
	//console.log( " " + data[id]);
	element.html(data[id] + '');
}


//Í¨Öª´°
 
(function () {
  var tId = 0;
  var ntId = 0;
  var span = document.createElement("span");
  document.body.appendChild(span);
  span.style.cssText="opacity: 0.1;position: fixed;text-align: center;line-height:50px;font-size: 20px;display: none;width: 20%;min-width: 250px;left:0px;right:0px;margin-right: auto;margin-left: auto;height: 50px;top: 400px;background-color: #d3d6d7;box-sizing: border-box;";
  
  showMessage = function(str,func){
	  span.style.display = 'block';
	  span.style.opacity = 0;
	  span.innerHTML = str;
      clearTimeout( tId);
      clearTimeout( ntId);
      var opacity = 0,
          showOut = false;

      (function(){
          if (!showOut){
              if(opacity<1.5)
              {
                  opacity+=0.02;
                  span.style.opacity = opacity;
              }
              else{
                  showOut=true;
              }
               ntId = setTimeout(arguments.callee,10);
          }
          else{
        	  if(opacity<0){
        		  if(func){
        			  func();
        		  } 
        		  return;
        	  }
        	  
              opacity-=0.02;
              span.style.opacity = opacity;
               ntId = setTimeout(arguments.callee,10);
          }
      }());

      tId = setTimeout(function(){
    	  span.style.display = 'none';
      },1500);
  };
}());