import matplotlib.pyplot as plt # type: ignore

# Sample data
n_values = [1, 2, 3, 4, 5, 6, 7, 8, 9]
standard = [60, 217, 517, 1000, 1700, 2705, 4037, 5742, 7854]
karatsuba = [2, 30, 114, 255, 428, 459, 664, 779, 925]

# Plotting
plt.figure(figsize=(6, 4))  # Set figure size
plt.plot(n_values, standard, marker='o', label='Standard')
plt.plot(n_values, karatsuba, marker='o', label='Karatsuba')
plt.xlabel('n')
plt.ylabel('(n)')
plt.title('Standard vs Karatsuba', fontsize=12, fontweight='bold')
plt.legend()
plt.show()