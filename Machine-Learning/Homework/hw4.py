import numpy as np

# Data
t = np.array([0.115, 0.116, 0.625])
y = np.array([-1, -1, 1])

# Construct X matrix
X = np.column_stack([np.ones(len(t)), np.cos(2 * np.pi * t), np.sin(2 * np.pi * t)])

# Least Squares Solution
theta_ls = np.linalg.inv(X.T @ X) @ X.T @ y
y_ls = X @ theta_ls
error_ls = np.linalg.norm(y_ls - y)**2

# Ridge Regression for lambda = 0.1
lambda_val = 0.1
theta_ridge = np.linalg.inv(X.T @ X + lambda_val * np.eye(X.shape[1])) @ X.T @ y
y_ridge = X @ theta_ridge
error_ridge = np.linalg.norm(y_ridge - y)**2

print(f"Least Squares Coefficients: {theta_ls}")
print(f"Ridge Regression Coefficients (lambda={lambda_val}): {theta_ridge}")

import matplotlib.pyplot as plt

# Generate lambdas from 0 to 10
lambdas = np.linspace(0, 10, 20)
errors = []

for lam in lambdas:
    theta_ridge = np.linalg.inv(X.T @ X + lam * np.eye(X.shape[1])) @ X.T @ y
    y_ridge = X @ theta_ridge
    error = np.linalg.norm(y_ridge - y)**2
    errors.append(error)

plt.plot(lambdas, errors)
plt.xlabel('Lambda')
plt.ylabel('Error (||y_lambda - y||^2)')
plt.title('Error as a function of lambda')
plt.show()

# Add random noise to data and compute ridge regression coefficients
np.random.seed(42)
noise_std = 0.5
errors_noisy = []

for _ in range(10):  # 10 noisy datasets
    y_noisy = y + np.random.normal(0, noise_std, size=y.shape)
    theta_ridge_noisy = np.linalg.inv(X.T @ X + lambda_val * np.eye(X.shape[1])) @ X.T @ y_noisy
    y_ridge_noisy = X @ theta_ridge_noisy
    error_noisy = np.linalg.norm(y_ls - y_ridge_noisy)**2
    errors_noisy.append(error_noisy)

avg_error_noisy = np.mean(errors_noisy)

print(f"Average error for noisy data: {avg_error_noisy}")
