from fastapi import Depends, Header, HTTPException
from .cognito_jwt import verify_jwt

def get_current_user(authorization: str = Header(...)):
    if not authorization.startswith("Bearer "):
        raise HTTPException(status_code=401, detail="Invalid authorization header")
    token = authorization.split(" ")[1]
    return verify_jwt(token)
