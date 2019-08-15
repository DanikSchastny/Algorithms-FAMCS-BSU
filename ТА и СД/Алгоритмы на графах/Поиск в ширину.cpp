#include<fstream>
#include<queue>

int main() {
	std::ifstream fin("input.txt");
	std::queue<int> queue;
	int n;
	fin >> n;

	int** matrix = new int*[n];
	int* way = new int[n];

	for (int i = 0; i < n; ++i) {
		matrix[i] = new int[n];
	}

	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			fin >> matrix[i][j];
		}
	}

	fin.close();

	bool* used = new bool[n];

	for (int i = 0; i < n; ++i) {
		used[i] = way[i] = 0;
	}
	int temp = 0;
	for (int i = 0; i < n; i++) {
		if (used[i] == false) {
			queue.push(i);
			++temp;
			used[i] = way[i] = temp;
			while (!queue.empty()) {
				int j = queue.front();
				queue.pop();
				for (int k = 0; k < n; ++k)
					if (matrix[j][k] == 1 && used[k] == 0) {
						queue.push(k);
						++temp;
						way[k] = temp;
						used[k] = true;
					}
			}
		}
	}

	std::ofstream fout("output.txt");
	for (int i = 0; i < n - 1; ++i) {
		fout << way[i] << " ";
	}

	fout << way[n - 1];

	delete[] way;
	delete[] used;
	for (int i = 0; i < n; ++i) {
		delete[] matrix[i];
	}
	delete[] matrix;
	fout.close();
	return 0;
}