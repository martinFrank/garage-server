<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" language="javascript">

        function restCall(method, path, target) {
            var request = new XMLHttpRequest();
            const cnt = '{"id":1,"content":"Hello, Stranger!"}';
            request.open(method,"http://192.168.0.64:8082"+path);
            request.setRequestHeader("CONTENT-TYPE","application/json");
            request.setRequestHeader("Cache-Control","max-age=0, no-cache, no-store, must-revalidate");
            request.addEventListener('load', function(event) {
                if (request.status >= 200 && request.status < 300) {
                    console.log(request.responseText);
                    document.getElementById(target).innerHTML = request.responseText;
                } else {
                    console.warn(request.statusText, request.responseText);
                }
            });
            request.send( null );
        }

    </script>
</head>

<body>

<div align="center"><h2>Garage Server</h2></div>
<br/>
<div align="center"> <iframe src="http://192.168.0.64:8088" title="Garage Web Cam" width="640" height="480"></iframe> </div>
<br/>
<div align="center">
    <button type="button" onclick="restCall('POST', '/garage/gate', 'gateStatus')" > <!--style="height:100px;width:300px"> -->
        garage change
    </button>
</div>

</body>
</html>