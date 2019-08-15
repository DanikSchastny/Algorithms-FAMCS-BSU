#include<iostream>
#include<fstream>
#include<vector>
#include<set>

int main() {
	int n, m;

	std::ifstream fin("input.txt");
	fin >> n >> m;
	std::vector<std::vector<std::pair<long long, long long> > > g(n);
	long long* d = new long long[n];
	long long a, b, c;
	for (int i = 0; i < m; ++i) {
		fin >> a >> b >> c;
		g[a - 1].push_back(std::make_pair(b - 1, c));
		g[b - 1].push_back(std::make_pair(a - 1, c));
	}
	fin.close();

	int start = 0;
	for (int i = 0; i < n; ++i) {
		d[i] = INT64_MAX;
	}
	d[start] = 0;
	std::set<std::pair<long long, long long> > way;
	way.insert(std::make_pair(d[start], start));
	while (!way.empty()) {
		long long v = way.begin()->second;
		way.erase(way.begin());
		for (int j = 0; j < g[v].size(); ++j) {
			long long to = g[v][j].first,
				len = g[v][j].second;
			if (d[v] + len < d[to]) {
				way.erase(std::make_pair(d[to], to));
				d[to] = d[v] + len;
				way.insert(std::make_pair(d[to], to));
			}
		}
	}
	std::ofstream fout("output.txt");
	fout << d[n - 1];
	fout.close();
	delete[] d;
	return 0;
}