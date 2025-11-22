# data-lake-flow
Python code is inside python-etl folder 
```text
python-etl/
├── app/
│   ├── main.py          # FastAPI app (Cognito integration)
│   ├── auth/
│   │   ├── cognito_jwt.py   # JWT verification logic (Cognito)
│   │   └── dependencies.py  # Dependency to get current user
│   └── routes/
│       ├── user_routes.py   # User-specific routes
│       └── admin_routes.py  # Admin-specific routes
├── glue_jobs/
│   ├── job1.py           # Example Glue job script 1
│   └── job2.py           # Example Glue job script 2
├── lambda_functions/
│   ├── function1.py      # Lambda function 1 (e.g., trigger for Glue)
│   └── function2.py      # Lambda function 2 (e.g., handle Cognito triggers)
├── requirements.txt      # Required dependencies (FastAPI, Glue, Lambda)
├── README.md             # Project documentation
└── .gitignore            # Ignore files/folders (e.g., virtualenv, logs, etc.)

# AuthForge - FastAPI Backend

This project is a secure backend API built with FastAPI that integrates with AWS Cognito for authentication.

## Project Structure:
- `app/`: Contains the FastAPI app code
  - `auth/`: Contains Cognito JWT verification and user dependency code
  - `routes/`: Contains user and admin API routes
- `requirements.txt`: List of required packages
- `README.md`: Project documentation

## Running the Project:
1. Install the dependencies:
    ```bash
    pip install -r requirements.txt
    ```
2. Run the FastAPI server:
    ```bash
    uvicorn app.main:app --reload --host 127.0.0.1 --port 8000
    ```

3. Test the endpoints:
    - Visit `http://127.0.0.1:8000` for the root endpoint.
    - Visit `http://127.0.0.1:8000/user/me` (with a valid Cognito JWT) for the user endpoint.
    - Admin endpoint is available at `/admin`.

## Dependencies:
- FastAPI
- Uvicorn
- python-jose (for JWT verification)
- Requests (for interacting with Cognito JWKS)

