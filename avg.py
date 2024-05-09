# Initialize the count data
count_data = [
    [60, 60, 60, 60, 60],
    [217, 217, 211, 217, 217],
    [517, 499, 505, 505, 505],
    [1000, 976, 982, 982, 994],
    [1700, 1688, 1700, 1688, 1706],
    [2705, 2675, 2693, 2687, 2705],
    [4037, 4007, 4001, 4007, 4025],
    [5742, 5694, 5696, 5706, 5718],
    [7854, 7818, 7788, 7830, 7836]
]

# Calculate the average count for each n
average_counts = []
for counts in count_data:
    average = sum(counts) / len(counts)
    average_counts.append(round(average, 2))

# Print the results
print("| n   | Average Count |")
print("|:---:|:-------------:|")
for n, avg_count in enumerate(average_counts, start=1):
    print(f"| {n}   | {avg_count:>13} |")