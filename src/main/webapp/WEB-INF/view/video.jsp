<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Video Player - TutsPlus Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="content/video.css">

</head>
<body>

	<!-- Video Elements with resource -->
	<video controls controlsList="nodownload">
		<source
			src="${pageContext.request.contextPath}/resources/video/${fileName}"
			type="video/mp4">
		<source
			src="${pageContext.request.contextPath}/resources/video/${fileName}"
			type="video/webm">
	</video>

	<!-- Video Player Control Buttons -->
	<!-- <div id="videoControls">
		<div id="seekBar">
			<span id="viewSeek"></span>
		</div>

		<button id="Play_Pause" onclick="togglePlay_Pause()">play>></button>
		<button id="Stop_Button" onclick="StopVideo()">Stop</button>
		<button id="rewind_Video" onclick="ManipulatePlaySpeed('-');">«
			Rewind</button>
		<button id="fastForward" onclick="ManipulatePlaySpeed('+');">Fast
			Forward »</button>
		<button id="decreaseVolume" onclick="AlterVolume('-')">-</button>
		<button id="volumeUp" onclick="AlterVolume('+')">+</button>
		<button id="mute" onclick="ToggleMute_UnMute()">mute</button>
	</div> -->

	<script>
// Select the Video Element
var video = document.getElementsByTagName("video")[0];
          
// Set the Default Browser Controls to false. i.e remove them
video.controls = false;
          
// Select the Play and Pause Button
var play_pause = document.getElementById("Play_Pause");		
			
// Add Event Listeners inorder to alter the value of play and pause button when user toggles
video.addEventListener('play', function() {
	play_pause.title = "pause";
	play_pause.innerHTML = "pause[0]";
			}, false);
	video.addEventListener('pause', function() {
	play_pause.title = "play";
	play_pause.innerHTML = "play>>";
			}, false);
  
// Event Listners to regularly update the progress Bar
	video.addEventListener('timeupdate', seekBar, false);\
    
// Event Listners to pause the video
	video.addEventListener('ended', function() { this.pause(); }, false);
			
// Function to stop the Video and reset the current time to zero(i.e starting point)
	function StopVideo() {
			video.pause();
			video.currentTime = 0;
			}
			
// Function to Toggle between Play and Pause
	function togglePlay_Pause() {
				if (video.paused || video.ended) {
					if (video.ended) video.currentTime = 0;
					video.play();
				}
				else {
					video.pause();
				}
			}
			
// Function to Alter the Volume in steps of 0.1
	function AlterVolume(direction) {
	var volume = Math.floor(video.volume * 10) / 10;
		video.muted = false;
		if (direction == "-") {
		if (volume <= 0.1) video.volume = 0;
			else video.volume -= 0.1;
				}
				else {
					if (volume >= 0.9) video.volume = 1;
					else video.volume += 0.1;   
				}
			}
			
// Toggles Volume between Mute and Unmute
	function ToggleMute_UnMute() {
	var mute = document.getElementById("mute");
	if (video.muted) {
		mute.innerHTML = "mute";
		video.muted = false;
				}
				else {
					mute.innerHTML = "unmute";
					video.muted = true;
				}
			}
			
// Update Seekbar regularly as the video plays
	function seekBar() {
	var value = 0;
	if (video.currentTime > 0) {
	value = Math.floor((100 / video.duration) * video.currentTime);
		                           }
	document.getElementById("viewSeek").style.width = value + "%";
			            }

// Function to manipulate the speed of playback both fastforward and rewind
	function ManipulatePlaySpeed(direction) {
	 if (video.playbackRate != undefined) {
	 if (direction == "-") video.playbackRate -= 1;
		else video.playbackRate += 1;
				}
		else {
					if (direction == "-") video.currentTime -= 1;
					else video.currentTime += 1;
				}
			}
			
// Playback from the point on the Seekbar where the user clicks
	function setPlayPosition(x) {				
		var seekBar = document.getElementById("seekBar");
		var value = (x - findPos(seekBar)).toFixed(2);
		var timeToSet = 
            ((video.duration / seekBar.offsetWidth).toFixed(2) * value).toFixed(2);
				video.currentTime = timeToSet;
			}
  
// Determine the real position of the object
		function findPos(obj) {
		var curleft = 0;
		if (obj.offsetParent) {
			do { curleft += obj.offsetLeft; } while (obj = obj.offsetParent);
				}
		return curleft;
			}	
  
// Event Listner for Mouse Events on the SeekBar
var seekBar = document.getElementById("seekBar").addEventListener
("mouseup", function(e) { setPlayPosition(e.pageX); }, false);
</script>
</body>
</html>