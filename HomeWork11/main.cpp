#include <omp.h>
#include <vector>
#include <iostream>
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

vector<long> mergeSort(vector<long> &vec, int threads) {
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
            {
                left = mergeSort(left, threads / 2);
            }
            #pragma omp section
            {
                right = mergeSort(right, threads - threads / 2);
            }
        }
    } else {
        left = mergeSort(left, 1);
        right = mergeSort(right, 1);
    }

    return merge(left, right);
}

int main() {
    unsigned int num = 10000000;
    vector<long> v(num);
    for (long i=0; i<num; ++i)
        v[i] = static_cast<long>((i * i) % num);
    v = mergeSort(v, 12);
    for (long i=0; i<num; ++i)
        cout << v[i] << "\n";
}