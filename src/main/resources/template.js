<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" language="javascript">

        function restCall(method, path, target) {
            var request = new XMLHttpRequest();
            const cnt = '{"id":1,"content":"Hello, Stranger!"}';
            request.open(method,"http://192.168.0.66:8080"+path);
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

<div align="center">
    <button type="button" onclick="restCall('GET', '/garage/gate', 'gateStatus')" > <!--style="height:100px;width:300px" > -->
        garage status
    </button>
    <button type="button" onclick="restCall('POST', '/garage/gate', 'gateStatus')" > <!--style="height:100px;width:300px"> -->
        garage change
    </button>
    <br/>
    <button type="button" onclick="restCall('GET', '/garage/light', 'lightStatus')" > <!--style="height:100px;width:300px" > -->
        light status
    </button>
    <button type="button" onclick="restCall('GET', '/garage/light', 'lightStatus')" > <!--style="height:100px;width:300px"> -->
        light change
    </button>
</div>

<br/>
<div align="center">
    <p id="gateStatus">{"state":"unknown","lastRequest":"unknown","isMoving":false}<br/>
    <p id="lightStatus">{"state":"unknown","lastRequest":"unknown","isMoving":false}<br/>
</p></div>

</body>
</html>