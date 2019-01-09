#include <omp.h>
#include <vector>
#include <iostream>
#include <random>
#include "time.h"
#include <chrono>
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
    if(vec.size() == 1) {
        return vec;
    }

    auto middle = vec.begin() + (vec.size() / 2);

    vector<long> left(vec.begin(), middle);
    vector<long> right(middle, vec.end());

    if (threads > 1) {
        omp_set_nested(1);
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

void parallelQuickSortOpt(vector<long> &vec, unsigned int start, unsigned int end, int threads) {
    unsigned int r = 0;
    if(start < end) {
        r = partition(vec, start, end);
        if (threads > 1) {
            omp_set_nested(1);
            #pragma omp parallel sections
            {
                #pragma omp section
                parallelQuickSortOpt(vec, start, r,  threads / 2);
                #pragma omp section
                parallelQuickSortOpt(vec, r+1, end,  threads - threads / 2);
            }
        } else {
            parallelQuickSortOpt(vec, start, r, 1);
            parallelQuickSortOpt(vec, r+1, end, 1);
        }
    }
}

void parallelQuickSort(vector<long> &vec, int threads) {
    parallelQuickSortOpt(vec, 0, static_cast<unsigned int>(vec.size()), threads);
}

int main() {
    unsigned int num = 10000000;
    vector<long> v(num);
    default_random_engine e(static_cast<unsigned int>(clock()));
    int threads[6] = {1, 2, 4, 6, 8, 12};
    for (int thread : threads) {
        for (long i=0; i<num; ++i)
            v[i] = static_cast<long>(e());
        auto startTime = std::chrono::high_resolution_clock::now();
        parallelQuickSort(v, thread);
        auto endTime = std::chrono::high_resolution_clock::now();
        cout << "Thread num: " << thread << ", Run time:" << (endTime-startTime).count()*1.0/1000000 << "ms" << endl;
    }
}