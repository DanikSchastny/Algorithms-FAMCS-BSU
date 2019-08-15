// MacrohardCPP.cpp : Этот файл содержит функцию "main". Здесь начинается и заканчивается выполнение программы.
//

#include <iostream>
#include<algorithm>
#include<set>
#include<fstream>

using namespace std;

int f(int i, int j, int* a, int** b) {

	if (b[i][j] < 0) {
		if (i > j) {
			b[i][j] = 0;
		}
		else {
			int fs = INT_MAX;
			for (int s = i; s <= j; ++s) {
				fs = min(fs, f(i, s - 1, a, b) + f(s + 1, j, a, b));
			}
			b[i][j] = a[j + 1] - a[i - 1] - 1 + fs;

		}

		return  b[i][j];
	}
	return b[i][j];

}


int main()
{

	std::ifstream fin("input.txt");
	int N, K;
	fin >> N;
	fin >> K;

	int* arr = new int[K + 2];
	int** arrfx = new int*[K + 2];
	for (int i = 0; i < K + 2; ++i) {
		arrfx[i] = new int[K + 2];
	}

	for (int i = 0; i < K + 2; ++i) {
		for (int j = 0; j < K + 2; ++j) {
			arrfx[i][j] = -1;
		}
	}
	arr[0] = 0;

	for (int i = 0; i < K; ++i) {
		fin >> arr[i + 1];

	}
	arr[K + 1] = N + 1;
	fin.close();

	std::ofstream fout("output.txt");
	sort(arr + 1, arr + K + 1);


	fout << f(1, K, arr, arrfx);
	delete[] arr;

	for (int i = 0; i < K + 2; ++i) {
		delete[] arrfx[i];
	}
	delete[] arrfx;
}