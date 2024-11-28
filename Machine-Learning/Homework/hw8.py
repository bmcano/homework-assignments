import numpy as np
import matplotlib.pyplot as plt
from matplotlib.patches import Ellipse

Sigma1 = np.array([[3, 1], [1, 3]])
Sigma2 = Sigma1 # problem shows them as the exact same
eigenvalues1, eigenvectors1 = np.linalg.eig(Sigma1)
eigenvalues2, eigenvectors2 = np.linalg.eig(Sigma2)

print(eigenvalues1, eigenvectors1, eigenvalues2, eigenvectors2)

def plot_cov_ellipse(mean, cov, ax, n_std=1.0):
    eigvals, eigvecs = np.linalg.eigh(cov)
    order = eigvals.argsort()[::-1]
    eigvals, eigvecs = eigvals[order], eigvecs[:, order]
    angle = np.degrees(np.arctan2(*eigvecs[:, 0][::-1]))
    print(angle)
    width, height = 2 * n_std * np.sqrt(eigvals)
    ellipse = Ellipse(mean, width, height, angle=45.0)
    ax.add_patch(ellipse)

fig, ax = plt.subplots()
plot_cov_ellipse([1, 0], Sigma1, ax,)
plot_cov_ellipse([0, 1], Sigma2, ax)
ax.plot(1, 0, 'bo')
ax.plot(0, 1, 'ro')
ax.set_xlim(-3, 3)
ax.set_ylim(-3, 3)
ax.set_aspect('equal')
ax.legend()
plt.xlabel('X1')
plt.ylabel('X2')
plt.title('Contours for p(x|w_1) and p(x|w_2)')
plt.grid()
plt.show()
