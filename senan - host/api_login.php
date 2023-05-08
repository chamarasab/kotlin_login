<?php
    include 'conn.php';
?>
<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
 
    $username = $_POST['username'];
    $password = $_POST['password'];
 
 
    $sql = "SELECT username,password FROM users WHERE username= '".$username."' AND password = '".$password."'  ";
 
    $result = mysqli_query($conn,$sql);

    $check = mysqli_fetch_array($result);
    
    if(isset($check)){
        echo "Welcome";
    } else{echo "😕 Oops!";}
  
 } else{echo "POST Error";}
 
 mysqli_close($conn);
?>

