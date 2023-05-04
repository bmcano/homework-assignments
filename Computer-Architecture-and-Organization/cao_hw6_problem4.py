# ECE:3550 
# Homework 6 Problem 4
# Brandon Cano
import matplotlib.pyplot as plt

"""
Question 4: Use Matlab or any other programming languages to plot a graph for the average memory 
access time (y-axis) versus hit rate 𝒉𝟏 (x-axis) for the two values 𝒉𝟐 = 𝟎.𝟕𝟓 and 𝒉𝟐 = 𝟎.𝟖𝟓. 
𝒉𝟏 should range from 0.5 to 1.0 with resolution 0.01.
L1 Cache access time is 1ms. 
Assume that the miss penalties are 15ms and 100ms for the L1 and L2 caches, respectively.
Attach both the graph and the source codes in your solution.
"""

def main():
    # t_ave = h_1*C_1 + (1 - h_1)(h_2*C_2 + (1 - h_2)*M)
    # h_1 = L1 Cache hit rate
    # h_2 = L2 Cache hit rate
    # C_1 = Cache access time in L1 Cache
    # C_2 = Cache access time in L2 Cache from L1 Cache
    # M = L2 Cache Miss Penalty 

    x, y = [], []

    t_ave = 0.0
    c_1 = 0.001     # 1ms   -> L1 cache access time
    c_2 = 0.015     # 15ms  -> L1 miss penalty
    m = 0.100       # 100ms -> L2 miss penalty

    # first iteration
    h_2 = 0.75
    for h1 in range(50, 100, 1):
        h_1 = h1 * 0.01     # to keep within the 0.5 - 1.0 range
        t_ave = h_1*c_1 + (1 - h_1)*(h_2*c_2 + (1 - h_2)*m)
        y.append(t_ave)
        x.append(h_1)

    # plot first set of points
    plt.plot(x, y, color="red", marker="o")
    
    # reset points
    x, y = [], []
    
    # second iteration
    h_2 = 0.85
    for h1 in range(50, 100, 1):
        h_1 = h1 * 0.01
        t_ave = h_1*c_1 + (1 - h_1)*(h_2*c_2 + (1 - h_2)*m)
        y.append(t_ave)
        x.append(h_1)

    # plot second set of points
    plt.plot(x, y, color="blue", marker="o")
    
    # labels
    plt.xlabel("Hit Rate")
    plt.ylabel('Average Memory Access Time')
    plt.title("HW6 - Problem 4")
    plt.legend(["H2 = 0.75", "H2 = 0.85"])

    # show graph
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()
