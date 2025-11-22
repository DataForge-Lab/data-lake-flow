from fastapi import FastAPI
from app.routes import user_routes, admin_routes

app = FastAPI(title="AuthForge - Secure Backend API")

# Include route modules
app.include_router(user_routes.router, prefix="/user", tags=["User"])
app.include_router(admin_routes.router, prefix="/admin", tags=["Admin"])

@app.get("/")
def root():
    return {"message": "Welcome to AuthForge API"}
