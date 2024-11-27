import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import multivariate_normal

mu1 = np.array([2, 0])
mu2 = np.array([3, 1])
Sigma1 = np.array([[2, 1], [1, 1]])
Sigma2 = np.array([[5, -1], [-1, 2]])

e = np.exp(1) 

x, y = np.meshgrid(np.linspace(-2, 6, 100), np.linspace(-2, 6, 100))
pos = np.dstack((x, y))
rv1 = multivariate_normal(mu1, Sigma1)
rv2 = multivariate_normal(mu2, Sigma2)

z1 = rv1.pdf(pos)
z2 = rv2.pdf(pos)
z_min_risk = z1 - e * z2

plt.figure(figsize=(10, 8))
plt.contour(x, y, z1, levels=[0.01], colors='blue', linestyles='dashed', label='Class 1 Boundary')
plt.contour(x, y, z2, levels=[0.01], colors='red', linestyles='dashed', label='Class 2 Boundary')

plt.contour(x, y, z1 - z2, levels=[0], colors='green', linewidths=2, label='Minimum Error Boundary')
plt.contour(x, y, z_min_risk, levels=[0], colors='black', linewidths=2, label='Minimum Risk Boundary')
print(z1 - z2)
print(z_min_risk)

plt.xlabel('$x_1$')
plt.ylabel('$x_2$')
plt.title('Decision Boundaries: Minimum Error and Minimum Risk')
plt.legend(['Class 1 Boundary', 'Class 2 Boundary', 'Minimum Error Boundary', 'Minimum Risk Boundary'])
plt.grid(True)
plt.show()
