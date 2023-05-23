import React, { useEffect, useState ,form} from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Paper, Button ,Container } from '@mui/material';
import SendIcon from '@mui/icons-material/Send';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import { AutoFixHighSharp } from '@mui/icons-material';



export default function Users() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[name,setName]=useState('')
    const[userName,setUserName]=useState('')
    const[password,setPassword]=useState('')
    const[email,setEmail]=useState('')
    const[role,setRole]=useState('')
    const[age,setAge]=useState()
    const[users,setUsers]=useState([])

    const handleClick=(e)=>{
        var age1 = parseInt(age);
        var password1 = parseInt(password);
        e.preventDefault()
        const user={name,userName,password1,email,role,age1}
        console.log(user)
        
        fetch("http://localhost:8080/api/v1/user/create",{
          mode:"no-cors",
          credentials: 'include',
          method:"POST",
          headers:{"Content-Type":"application/json"},  
          body:JSON.stringify(user),
    
      }).then(()=>{
        console.log("New User added")
      })
    }
  return (
    <Container>
        <form  noValidate autoComplete="off">
        <Paper elevation={3} style={paperStyle}>
        <h2>Add User <PersonAddIcon fullWidth margin="15"/></h2>
       
        
      <TextField id="outlined-basic" label="Name" variant="outlined" fullWidth margin="dense" value={name}
      onChange={(e)=>setName(e.target.value)}/>
      <TextField id="outlined-basic" label="UserName" variant="outlined" fullWidth margin="dense" value={userName}
      onChange={(e)=>setUserName(e.target.value)}/>
      <TextField id="outlined-basic" label="password" variant="outlined" fullWidth margin="dense" value={password} 
      onChange={(e)=>setPassword(e.target.value)}/>
      <TextField id="outlined-basic" label="Email" variant="outlined" fullWidth margin="dense" value={email}
      onChange={(e)=>setEmail(e.target.value)}/>
      <TextField id="outlined-basic" label="Role" variant="outlined" fullWidth margin="dense" value={role}
      onChange={(e)=>setRole(e.target.value)}/>
      <TextField id="outlined-basic" label="Age" variant="outlined" fullWidth margin="dense" value={age}
      onChange={(e)=>setAge(e.target.value)}/>  
    <Button variant="contained" endIcon={<SendIcon />} onClick={handleClick}>
  Send
    </Button>
      </Paper>
      </form> 
    </Container>
  );
}
