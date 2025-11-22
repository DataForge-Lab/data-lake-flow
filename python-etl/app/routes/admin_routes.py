from fastapi import APIRouter

router = APIRouter()

@router.get("/admin")
def get_admin_info():
    return {"message": "Admin info secured by Cognito"}
