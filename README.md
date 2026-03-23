### Benchmark 
```text
1 thread:  2758 ms
2 threads: 1568 ms
3 threads: 1313 ms
4 threads: 1221 ms
5 threads: 1182 ms
6 threads: 1126 ms
7 threads: 1097 ms
8 threads: 1034 ms
9 threads: 1027 ms
10 threads: 929 ms
11 threads: 825 ms
12 threads: 823 ms
13 threads: 822 ms
14 threads: 810 ms
15 threads: 789 ms
16 threads: 782 ms
```
---

### Edge Detection Output

Here is a visual demonstration of the Sobel operator applied to a sample image. The algorithm successfully processes the source image into grayscale and accurately highlights all major edges, creating a clear edge map.

| Original Image (`input.jpg`) | Sobel Output (`output.png`) |
| :---: | :---: |
| <img src="input.jpg" width="400" alt="Original input image"> | <img src="output.png" width="400" alt="Sobel operator output showing edges"> |

---

### Multithreading Performance Benchmark

To analyze the efficiency of the multithreaded implementation, I conducted an automated benchmark, measuring the execution time for the Sobel convolution phase across **1 to 16 threads**. The processing was performed on a **16-core logical processor** system.

#### Key Findings

The chart below clearly demonstrates the performance gain from parallel processing:

* **Max Scaling:** Transitioning from 1 to 2 threads provides the most significant speedup, nearly halving the processing time.
* **Optimal Range:** Execution time scales well up to approximately **11 threads**, achieving a maximum speedup of **~71%** (from 2758 ms to 782 ms).
* **Diminishing Returns:** Above 11 threads, the performance curve flattens. For this image size and workload, the overhead of thread management and synchronization begins to outweigh the benefits of additional parallel compute power.

<img width="640" height="480" alt="sobel_performance_chart" src="https://github.com/user-attachments/assets/94e0b74f-4af3-42d2-a3cd-9752401c790d" />



