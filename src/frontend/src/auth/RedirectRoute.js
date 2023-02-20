import React, { Children } from 'react'
import { Navigate } from 'react-router-dom'
import { UserAuth } from '../auth/AuthContext'

const RedirectRoute = ({children}) => {

    const {user} = UserAuth();
    if(!user){
        return children;
    }else{
      return <Navigate to="/"/>
  }
}

export default RedirectRoute