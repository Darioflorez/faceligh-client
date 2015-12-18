/**
 * Created by johan on 15/12/15.
 */
var socket = io('http://130.237.84.84:3000');
$('form').submit(function(){
    socket.emit('chat message', $('#name').val() + ": " +  $('#m').val());
    $('#m').val('');
    return false;
});
socket.on('chat message', function(msg){
    $('#messages').append($('<li>').text(msg));
});