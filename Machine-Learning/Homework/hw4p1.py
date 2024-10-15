import numpy as np
import matplotlib.pyplot as plt

t = np.array([0.115, 0.116, 0.625])
y = np.array([-1, -1, 1])
X = np.column_stack([np.ones(len(t)), np.cos(2 * np.pi * t), np.sin(2 * np.pi * t)])

# Least Squares Solution (part A)
theta_ls = np.linalg.inv(X.T @ X) @ X.T @ y
y_ls = X @ theta_ls
error_ls = np.linalg.norm(y_ls - y)**2
print(f"Least Squares Coefficients: {theta_ls}")

# Ridge Regression for lambda = 0.1 (part B)
lambda_val = 0.1
theta_ridge = np.linalg.inv(X.T @ X + lambda_val * np.eye(X.shape[1])) @ X.T @ y
y_ridge = X @ theta_ridge
error_ridge = np.linalg.norm(y_ridge - y)**2
print(f"Ridge Regression Coefficients (lambda={lambda_val}): {theta_ridge}")

# part C
lambdas = np.linspace(0, 10, 20)
errors = []
for lam in lambdas:
    theta_ridge = np.linalg.inv(X.T @ X + lam * np.eye(X.shape[1])) @ X.T @ y
    y_ridge = X @ theta_ridge
    error = np.linalg.norm(y_ridge - y)**2
    errors.append(error)

plt.plot(lambdas, errors)
plt.xlabel('Lambda')
plt.ylabel('Error')
plt.title('Error as a function of lambda: ||y_lambda - y||^2')
plt.show()

# Part D
np.random.seed(42)
errors_noisy_ls = []
for _ in range(10):
    y_noisy = y + np.random.normal(0, 0.5, size=y.shape)
    theta_ls_noisy = np.linalg.inv(X.T @ X) @ X.T @ y_noisy
    y_ls_noisy = X @ theta_ls_noisy
    error_noisy_ls = np.linalg.norm(y_ls - y_ls_noisy)**2
    errors_noisy_ls.append(error_noisy_ls)
avg_error_noisy_ls = np.mean(errors_noisy_ls)
print(f"Average least-squares error from noisy data: {avg_error_noisy_ls}")

# Part E
errors_noisy_ridge = []
for _ in range(10):
    y_noisy = y + np.random.normal(0, 0.5, size=y.shape)
    theta_ridge_noisy = np.linalg.inv(X.T @ (X + lambda_val * np.eye(X.shape[1]))) @ X.T @ y_noisy
    y_ridge_noisy = X @ theta_ridge_noisy
    error_noisy_ridge = np.linalg.norm(y_noisy - y_ridge_noisy)**2
    errors_noisy_ridge.append(error_noisy_ridge)
avg_error_noisy_ridge = np.mean(errors_noisy_ridge)
print(f"Average ridge regression error from noisy data: {avg_error_noisy_ridge}")
