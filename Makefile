all: compile demo
compile:
	-chmod u+x ./*.sh
	./compile.sh
demo:
	-chmod u+x ./*.sh
	rm -rf output_fast
	mkdir output_fast
	@echo [DEMO] running ThinkD Fast Ver.
	./run_fast.sh example_graph.txt output_fast 0.3 3
	@echo [DEMO] estimated global and local triangle counts are saved in output
	rm -rf output_acc
	mkdir output_acc
	@echo [DEMO] running ThinkD Accurate Ver.
	./run_acc.sh example_graph.txt output_acc 72000 3
	@echo [DEMO] estimated global and local triangle counts are saved in output
