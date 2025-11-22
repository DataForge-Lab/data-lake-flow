# data-lake-flow
Python code is inside python-etl folder 
python-etl/
 ├── app/
 │   ├── main.py                    # FastAPI app (Cognito integration)
 │   ├── auth/
 │   │   ├── cognito_jwt.py         # JWT verification logic (Cognito)
 │   │   └── dependencies.py        # Dependency to get current user
 │   └── routes/
 │       ├── user_routes.py         # User-specific routes
 │       └── admin_routes.py        # Admin-specific routes
 ├── glue_jobs/
 │   ├── job1.py                    # Example Glue job script 1
 │   └── job2.py                    # Example Glue job script 2
 ├── lambda_functions/
 │   ├── function1.py               # Lambda function 1 (e.g., trigger for Glue)
 │   └── function2.py               # Lambda function 2 (e.g., handle Cognito triggers)
 ├── requirements.txt               # Required dependencies (FastAPI, Glue, Lambda)
 ├── README.md                      # Project documentation
 └── .gitignore                     # Ignore files/folders (e.g., virtualenv, logs, etc.)
