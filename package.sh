rm ThinkD-1.0.tar.gz
rm -rf ThinkD-1.0
mkdir ThinkD-1.0
cp -R ./{run_fast.sh,run_acc.sh,compile.sh,package.sh,src,data.html,./resources,fastutil-7.2.0.jar,./output_fast,./output_acc,Makefile,README.txt,*.jar,example_graph.txt,user_guide.pdf} ./ThinkD-1.0
tar cvzf ThinkD-1.0.tar.gz --exclude='._*' --exclude="*.iml" ./ThinkD-1.0
rm -rf ThinkD-1.0
echo done.
