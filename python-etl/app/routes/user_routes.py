from fastapi import APIRouter, Depends
from app.auth.dependencies import get_current_user

router = APIRouter()

@router.get("/me")
def read_me(user=Depends(get_current_user)):
    return {"email": user.get("email"), "sub": user.get("sub")}
