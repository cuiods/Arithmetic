#include <omp.h>
#include <vector>
#include <iostream>
#include "time.h"
using namespace std;

vector<long> merge(const vector<long>& left, const vector<long>& right) {
    vector<long> result;
    unsigned left_it = 0, right_it = 0;

    while(left_it < left.size() && right_it < right.size()) {
        if(left[left_it] < right[right_it]) {
            result.push_back(left[left_it]);
            left_it++;
        } else {
            result.push_back(right[right_it]);
            right_it++;
        }
    }

    // Push the remaining data from both vectors onto the resultant
    while(left_it < left.size()) {
        result.push_back(left[left_it]);
        left_it++;
    }

    while(right_it < right.size()) {
        result.push_back(right[right_it]);
        right_it++;
    }

    return result;
}

unsigned int partition(vector<long>& A, unsigned int p,unsigned int q) {
    long x= A[p];
    unsigned int i=p;
    unsigned int j;

    for(j = p+1; j < q; j++) {
        if(A[j] <= x) {
            i = i+1;
            swap(A[i],A[j]);
        }
    }

    swap(A[i],A[p]);
    return i;
}

vector<long> parallelMergeSort(vector<long> &vec, int threads) {
    // Termination condition: List is completely sorted if it
    // only contains a single element.
    if(vec.size() == 1) {
        return vec;
    }

    // Determine the location of the middle element in the vector
    auto middle = vec.begin() + (vec.size() / 2);

    vector<long> left(vec.begin(), middle);
    vector<long> right(middle, vec.end());

    // Perform a merge sort on the two smaller vectors
    if (threads > 1) {
        #pragma omp parallel sections
        {
            #pragma omp section
            left = parallelMergeSort(left, threads / 2);
            #pragma omp section
            right = parallelMergeSort(right, threads - threads / 2);
        }
    } else {
        left = parallelMergeSort(left, 1);
        right = parallelMergeSort(right, 1);
    }

    return merge(left, right);
}

void parallelQuickSort(vector<long> &vec, unsigned int start, unsigned int end, int threads) {
    unsigned int r = 0;
    if(start < end) {
        r = partition(vec, start, end);
        if (threads > 1) {
            #pragma omp parallel sections
            {
                #pragma omp section
                parallelQuickSort(vec, start, r,  threads / 2);
                #pragma omp section
                parallelQuickSort(vec, r+1, end,  threads - threads / 2);
            }
        } else {
            parallelQuickSort(vec, start, r, 1);
            parallelQuickSort(vec, r+1, end, 1);
        }
    }
}

int main() {
    unsigned int num = 100000000;
    vector<long> v(num);
    for (long i=0; i<num; ++i)
        v[i] = static_cast<long>((i * i) % num);
    clock_t startTime = clock();
    parallelQuickSort(v,0 ,num, 12);
    clock_t endTime = clock();
    cout << "Run time:" << (endTime-startTime)*1.0/CLOCKS_PER_SEC*1000 << "ms" << endl;
//    for (long i=0; i<num; ++i)
//        cout << v[i] << "\n";
}