#include<fstream>
#include<set>

int main() {
	std::ifstream fin("expo.in");
	int n, m;
	fin >> n >> m;
	bool* rooms = new bool[n + 1];
	for (int i = 1; i < n + 1; ++i) {
		rooms[i] = false;
	}
	int a, b, ammount = 0;
	std::multiset<int> set;
	for (int i = 0; i < m; ++i) {
		fin >> a >> b;
		if (!rooms[a] && !rooms[b]) {
			rooms[a] = rooms[b] = true;
			ammount += 2;
			set.insert(a);
			set.insert(b);
		}
	}


	fin.close();

	std::ofstream fout("expo.out");
	fout << ammount << std::endl;
	for (auto i = set.begin(); i != set.end(); i++)
		fout << *i << " ";
	fout.close();
	delete[] rooms;
	
}