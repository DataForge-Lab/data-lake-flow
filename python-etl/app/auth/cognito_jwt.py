import requests
from jose import jwt
from fastapi import HTTPException

# Replace with your AuthForge Cognito info
COGNITO_POOL_ID = "us-east-1_XXXXXXXXX"
COGNITO_REGION = "us-east-1"
COGNITO_APP_CLIENT_ID = "XXXXXXXXXXXX"

JWKS_URL = f"https://cognito-idp.{COGNITO_REGION}.amazonaws.com/{COGNITO_POOL_ID}/.well-known/jwks.json"
JWKS = requests.get(JWKS_URL).json()

def verify_jwt(token: str):
    try:
        header = jwt.get_unverified_header(token)
        key = next(k for k in JWKS["keys"] if k["kid"] == header["kid"])
        claims = jwt.decode(token, key, algorithms=["RS256"], audience=COGNITO_APP_CLIENT_ID)
        return claims
    except Exception as e:
        raise HTTPException(status_code=401, detail=f"Invalid token: {str(e)}")
