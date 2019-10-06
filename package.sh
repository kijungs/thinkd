rm ThinkD-2.0.tar.gz
rm -rf ThinkD-2.0
mkdir ThinkD-2.0
cp -R ./{run_fast.sh,run_acc.sh,run_spot.sh,compile.sh,package.sh,src,data.html,./resources,fastutil-7.2.0.jar,./output_fast,./output_acc,./output_spot,Makefile,README.txt,*.jar,example_graph.txt,example_graph_with_timestamps.txt,user_guide.pdf} ./ThinkD-2.0
tar cvzf ThinkD-2.0.tar.gz --exclude='._*' --exclude='.DS_Store' --exclude="*.iml" ./ThinkD-2.0
rm -rf ThinkD-2.0
echo done.
