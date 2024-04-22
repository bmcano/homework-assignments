% load files
load Xdata.mat
load idx.mat

% question 1 code
nw_cities = Xdata(idx == 1,:);
se_cities = Xdata(idx == 2,:);

trainset = [nw_cities; se_cities];
labelset = [ones(length(find(idx == 1)),1); -1*ones(length(find(idx == 2)),1)];

SVMModel = fitcsvm(trainset,labelset);
m = - SVMModel.Beta(1) / SVMModel.Beta(2)
b = SVMModel.Bias

% question 2 code
figure;
hold on;
plot(nw_cities(:,1), nw_cities(:,2), 'bo');
plot(se_cities(:,1), se_cities(:,2), 'r+');

x_vals = linspace(-60,100,100);
y_vals = m * x_vals + b;
plot(x_vals, y_vals, 'k--', 'LineWidth', 2);

xlabel('South');
ylabel('West');
title('Separating Line');
legend('NW Cities', 'SE Cities', 'Separating Line');
axis([-60 100 -100 180]);
grid on;

% question 3 code
giessen = predict(SVMModel, [0 22]);
if giessen == 1
    disp('Giessen is in the NW cluster.');
else
    disp('Giessen is in the SE cluster.');
end

