import React, { useEffect, useState , dispatch} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Paper,Container,Button ,ListItem , Grid , List , ListItemAvatar,ListItemText,Avatar ,Demo,Typography} from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import PersonAddIcon from '@mui/icons-material/PersonAdd';

import { message } from 'ant-design-vue';
import axios from 'axios';


export default function Users() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[name,setName]=useState('')
    const[username,setUserName]=useState('')
    const[password1,setPassword1]=useState('')
    const[email,setEmail]=useState('')
    const[role,setRole]=useState('')
    const[age1,setAge1]=useState()
    const[users,setUsers]=useState([])
    //when the button is clicked all values from the form will be sent to this function
    const handleClick=(e)=>{
        var age = parseInt(age1);
        var password = parseInt(password1);
        e.preventDefault()

        console.log({name,username,password,email,role,age})
        
        const headers = {
            'Content-Type': 'application/json',
          }
          //values are passed to the API endpoint using post method
          axios.post("http://localhost:8080/api/v1/user/create", {name,username,password,email,role,age}, {
              headers: headers
            })
            .then((response) => {
              console.log("user added")
            })
            .catch((error) => {
                console.log(error)
            })
    }
    //use effect is react function that works right when the page is initiated or refreshed so it will keep calling the get method
    //so get function will work everytime the page is refreshed
    useEffect(()=>{
        axios
        .get("http://localhost:8080/api/v1/user/get")
        .then(function (response) {
            console.log(response);
            setUsers(response.data)
                })},[])
                
               

  
  return (
    <Container>
        <form  noValidate autoComplete="off">
        <Paper elevation={3} style={paperStyle}>
        <h2>Add User <PersonAddIcon fullWidth margin="15"/></h2>
       
        
      <TextField id="outlined-basic" label="Name" variant="outlined" fullWidth margin="dense" value={name}
      onChange={(e)=>setName(e.target.value)}/>
      <TextField id="outlined-basic" label="UserName" variant="outlined" fullWidth margin="dense" value={username}
      onChange={(e)=>setUserName(e.target.value)}/>
      <TextField id="outlined-basic" label="password" variant="outlined" fullWidth margin="dense" value={password1} 
      onChange={(e)=>setPassword1(e.target.value)}/>
      <TextField id="outlined-basic" label="Email" variant="outlined" fullWidth margin="dense" value={email}
      onChange={(e)=>setEmail(e.target.value)}/>
      <TextField id="outlined-basic" label="Role" variant="outlined" fullWidth margin="dense" value={role}
      onChange={(e)=>setRole(e.target.value)}/>
      <TextField id="outlined-basic" label="Age" variant="outlined" fullWidth margin="dense" value={age1}
      onChange={(e)=>setAge1(e.target.value)}/>  
    <Button variant="contained" endIcon={<SendIcon />} onClick={handleClick}>
  Send
    </Button>
      </Paper>
      </form> 

      <h1>Users</h1>

<Paper elevation={3} style={paperStyle}>

  {users.map(user=>(
    <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={users.id}>
     Id:{user.id}<br/>
     Name:{user.name}<br/>
     Username:{user.username}<br/>
     Email:{user.email}<br/>
     Role:{user.role}<br/>
     Age:{user.age}<br/>

    </Paper>
  ))
}


</Paper>

    </Container>
  );
}
