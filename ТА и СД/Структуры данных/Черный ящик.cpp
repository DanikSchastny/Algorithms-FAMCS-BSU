#include<fstream>
#include<queue>
#include<functional>
#include<vector>
#include<iostream>

void soutElements(std::priority_queue <int, std::vector <int>, std::less<int> > ub) {
	std::cout << "Ub" << std::endl;
	
	std::priority_queue <int, std::vector <int>, std::less<int> > ubb;
	for (int i = 0; i < ub.size(); ++i) {
		std::cout << ub.top() << " ";
		ubb.push(ub.top());
		ub.pop();
	}

	for (int i = 0; i < ubb.size(); ++i) {
		ub.push(ubb.top());
		ubb.pop();
	}

	std::cout << std::endl;
}

void soutElements(std::priority_queue <int, std::vector <int>, std::greater<int> > ub) {
	std::cout << "Voz" << std::endl;
	std::priority_queue <int, std::vector <int>, std::greater<int> > ubb;
	for (int i = 0; i < ub.size(); ++i) {
		std::cout << ub.top() << " ";
		ubb.push(ub.top());
		ub.pop();
	}

	for (int i = 0; i < ubb.size(); ++i) {
		ub.push(ubb.top());
		ubb.pop();
	}

	std::cout << std::endl;
}

int main() {
	std::priority_queue <int, std::vector <int>, std::less<int> > ub;
	std::priority_queue<int, std::vector<int>, std::greater<int>> voz;

	std::ifstream fin("input.txt");
	int m, n;
	fin >> m >> n;

	int* elements = new int[m];
	for (int i = 0; i < m; ++i) {
		fin >> elements[i];
	}



	int currentIndex = 1, i = 0, totalSize = 0, currentPosition;
	ub.push(elements[0]);
	int term = 0;
	std::ofstream fout("output.txt");


	for (int j = 0; j < n; ++j) {
		fin >> term;
		while (currentIndex != term) {
			if (elements[currentIndex] < ub.top()) {
				voz.push(ub.top());
				ub.pop();
				ub.push(elements[currentIndex++]);
			}
			else {
				voz.push(elements[currentIndex++]);
			}
		}


		if (currentIndex == term) {
			if (i == n - 1)
				fout << ub.top();
			else
				fout << ub.top() << " ";
			++i;

			if (voz.size() != 0) {
				ub.push(voz.top());
				voz.pop();
			}
			else {
				ub.push(elements[currentIndex++]);
			}
		}

	}



	

		
	delete[] elements;

	fout.close();
	return 0;


}