#include<iostream>
#include<cstdlib>
#include<fstream>
#include<algorithm>
#include<map>


int main() {

	std::ifstream fin("input.txt");
	int n, term, doubleNumber;
	fin >> n;
	int* numbers = new int[n];
	std::map<long long, int> map;  

	for(int i = 0; i < n*n; ++i){
		fin >> term; map[term]++;
	}
	fin.close();


	std::ofstream fout("output.txt");
	term = (*map.begin()).first / 2;
	numbers[0] = term;
	fout << numbers[0] << "\n";

    (*map.begin()).second == 1 ? map.erase((*map.begin()).first) : map[(*map.begin()).first]--;
	
	for (int i = 1; i < n; i++) {
		numbers[i] = (*map.begin()).first - term;
		fout << numbers[i] << "\n";

		if (i != n - 1) {
			(map[2 * numbers[i]] == 1) ? map.erase(2 * numbers[i]) : map[2 * numbers[i]]--;

			for (int j = i - 1; j > -1; j--) {
				map[numbers[i] + numbers[j]] <= 2 ? map.erase(numbers[i] + numbers[j]) : map[numbers[i] + numbers[j]] -= 2;
			}
		}
		else
			break;
	}

	fout.close();
	delete[] numbers;

	return 0;
}