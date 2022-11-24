import React, { Children } from 'react'
import { Navigate } from 'react-router-dom'
import { UserAuth } from '../auth/AuthContext'

const Protectedroute = ({children}) => {

    const {user} = UserAuth();
    if(!user){
        return <Navigate to="/login"/>
    }else{
  return children;
  }
}

export default Protectedroute