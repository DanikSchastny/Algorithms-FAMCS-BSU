#include <iostream>
#include<set>
#include<fstream>
#include<vector>

int main()
{
	std::ifstream fin("input.txt");
	if (!fin.is_open()) {
		std::cout << '+';
	}
	int n, m;

	fin >> n >> m;

	std::vector<std::multiset<int>> vect(n);

	int first, second;
	for (int i = 0; i < m; ++i) {
		fin >> first >> second;

		vect[first - 1].insert(second);
		vect[second - 1].insert(first);
	}

	fin.close();

	std::ofstream fout("output.txt");

	for (int i = 0; i < n; ++i) {
		fout << vect[i].size() << " ";

		for (int number : vect[i]) {
			fout << number << " ";
		}
		fout << "\n";
	}

	fout.close();
}