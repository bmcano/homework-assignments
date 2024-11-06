import numpy as np
import matplotlib.pyplot as plt

beta_n = np.e
beta_t = 1 
x = np.linspace(0, 10, 500)
p_normal = (1 / beta_n) * np.exp(-x / beta_n)
p_tumor = (1 / beta_t) * np.exp(-x / beta_t)

# 1a
plt.figure(figsize=(10, 6))
plt.plot(x, p_normal, label=r'$p(x|\text{normal})$', color='blue')
plt.plot(x, p_tumor, label=r'$p(x|\text{tumor})$', color='red')
plt.xlabel('x (Pixel Intensity)')
plt.ylabel('Probability Density')
plt.title('Probability Density Functions for Normal and Tumor Tissue')
plt.legend()
plt.grid(True)
plt.show()

# 1b
decision_boundary_equal_prior = (beta_n * beta_t / (beta_n - beta_t)) * np.log(beta_n / beta_t)

plt.figure(figsize=(10, 6))
plt.plot(x, p_normal, label=r'$p(x|\text{normal})$', color='blue')
plt.plot(x, p_tumor, label=r'$p(x|\text{tumor})$', color='red')
plt.axvline(x=decision_boundary_equal_prior, color='green', linestyle='--', label='Decision Boundary (Equal Priors)')
plt.xlabel('x (Pixel Intensity)')
plt.ylabel('Probability Density')
plt.title('Decision Boundary with Equal Priors')
plt.legend()
plt.grid(True)
plt.show()

# 1c
prior_ratio = np.exp(0.5)
decision_boundary_different_prior = (beta_n * beta_t / (beta_n - beta_t)) * np.log(beta_n * prior_ratio / beta_t)

plt.figure(figsize=(10, 6))
plt.plot(x, p_normal, label=r'$p(x|\text{normal})$', color='blue')
plt.plot(x, p_tumor, label=r'$p(x|\text{tumor})$', color='red')
plt.axvline(x=decision_boundary_equal_prior, color='green', linestyle='--', label='Decision Boundary (Equal Priors)')
plt.axvline(x=decision_boundary_different_prior, color='black', linestyle='--', label='Decision Boundary (Adjusted Priors)')
plt.xlabel('x (Pixel Intensity)')
plt.ylabel('Probability Density')
plt.title('Decision Boundary with Adjusted Priors')
plt.legend()
plt.grid(True)
plt.show()

# 1d
prior_tumor = 1
prior_normal = np.e * prior_tumor

posterior_normal = (p_normal * prior_normal) / (p_normal * prior_normal + p_tumor * prior_tumor)
posterior_tumor = (p_tumor * prior_tumor) / (p_normal * prior_normal + p_tumor * prior_tumor)

print(posterior_normal)
print(posterior_tumor)

# Plotting
plt.figure(figsize=(10, 6))
plt.plot(x, posterior_normal, label=r'$p(\text{normal}|x)$ (Posterior for Normal Tissue)', color='blue')
plt.plot(x, posterior_tumor, label=r'$p(\text{tumor}|x)$ (Posterior for Tumor Tissue)', color='red')
plt.xlabel('x (Pixel Intensity)')
plt.ylabel('Posterior Probability')
plt.title('Posterior Probabilities for Normal and Tumor Tissue')
plt.legend()
plt.grid(True)
plt.show()

# 1e
threshold = 0.9

normal_region = posterior_normal >= threshold
tumor_region = posterior_tumor >= threshold
reject_region = ~(normal_region | tumor_region)

plt.figure(figsize=(10, 6))
plt.plot(x, posterior_normal, label=r'$p(\text{normal}|x)$ (Posterior for Normal Tissue)', color='blue')
plt.plot(x, posterior_tumor, label=r'$p(\text{tumor}|x)$ (Posterior for Tumor Tissue)', color='red')

# Highlight the decision regions
plt.fill_between(x, 0, 1, where=normal_region, color='blue', alpha=0.1, label='Classify as Normal')
plt.fill_between(x, 0, 1, where=tumor_region, color='red', alpha=0.1, label='Classify as Tumor')
plt.fill_between(x, 0, 1, where=reject_region, color='gray', alpha=0.2, label='Reject Classification')

plt.xlabel('x (Pixel Intensity)')
plt.ylabel('Posterior Probability')
plt.title('Decision Regions with 90% Confidence Threshold')
plt.legend()
plt.grid(True)
plt.show()

# 1f
cost_ratio = 148.41

normal_region_cost_adjusted = posterior_normal > cost_ratio * posterior_tumor
tumor_region_cost_adjusted = ~normal_region_cost_adjusted

plt.figure(figsize=(10, 6))
plt.plot(x, posterior_normal, label=r'$p(\text{normal}|x)$ (Posterior for Normal Tissue)', color='blue')
plt.plot(x, posterior_tumor, label=r'$p(\text{tumor}|x)$ (Posterior for Tumor Tissue)', color='red')
plt.fill_between(x, 0, 1, where=normal_region_cost_adjusted, color='blue', alpha=0.1, label='Classify as Normal')
plt.fill_between(x, 0, 1, where=tumor_region_cost_adjusted, color='red', alpha=0.1, label='Classify as Tumor')

plt.xlabel('x (Pixel Intensity)')
plt.ylabel('Posterior Probability')
plt.title('Decision Regions with Asymmetric Misclassification Costs')
plt.legend()
plt.grid(True)
plt.show()
