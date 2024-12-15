import numpy as np
import matplotlib.pyplot as plt

theta_0 = 1
sigma = 3
lambda_values = np.linspace(0.1, 100, 500)
N_values = [2, 10, 1000]

def bias_ridge(lambda_val, N):
    return -lambda_val * theta_0 / (N + lambda_val)

def variance_ridge(lambda_val, N):
    return N * sigma**2 / (N + lambda_val)**2

def mse_ridge(lambda_val, N):
    bias_sq = (lambda_val * theta_0 / (N + lambda_val))**2
    variance = N * sigma**2 / (N + lambda_val)**2
    return bias_sq + variance

plt.figure(figsize=(18, 5))
plt.subplot(1, 3, 1)
for N in N_values:
    bias = bias_ridge(lambda_values, N)
    plt.plot(lambda_values, bias, label=f'N={N}')
plt.title('Bias vs Lambda')
plt.xlabel('Lambda')
plt.ylabel('Bias')
plt.legend()

plt.subplot(1, 3, 2)
for N in N_values:
    variance = variance_ridge(lambda_values, N)
    plt.plot(lambda_values, variance, label=f'N={N}')
plt.title('Variance vs Lambda')
plt.xlabel('Lambda')
plt.ylabel('Variance')
plt.legend()

plt.subplot(1, 3, 3)
for N in N_values:
    mse = mse_ridge(lambda_values, N)
    plt.plot(lambda_values, mse, label=f'N={N}')
plt.title('MSE vs Lambda')
plt.xlabel('Lambda')
plt.ylabel('MSE')
plt.legend()

plt.tight_layout()
plt.show()
