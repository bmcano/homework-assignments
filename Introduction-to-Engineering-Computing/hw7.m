% Name: Brandon Cano
% Date: 12/4/20
% Filename: hw7.m
% Description: To analyze data from two txt files and see which
% person is correct with their claims

% opens the files
fid = fopen("inventor1.txt");
Fid = fopen("inventor2.txt");
% reads the data from files
I1 = importdata("inventor1.txt");
I2 = importdata("inventor2.txt");

% puts all the data into matrices
for i = 1:10
    Operational1(i) = I1(i,1);
    Operational2(i) = I2(i,1);
    
    Efficiency1(i) = I1(i,4)/I1(i,5);
    Carnot1(i) = 1 - (I1(i,2)/I1(i,3));
    
    Efficiency2(i) = I2(i,4)/I2(i,5);
    Carnot2(i) = 1 - (I2(i,2)/I2(i,3));
end

% plots the data for inventor 1
subplot(2,1,1)
plot(Operational1, Efficiency1)
hold on;
plot(Operational1, Carnot1)
title('Efficiency and Carnot Efficiency vs. Run Number for inventor 1')
xlabel('Run Number')
ylabel('Efficiency')
legend('Efficiency','Carnot Efficiency')
axis([1,10,0,0.5])

% plots the data for inventor 2
subplot(2,1,2)
plot(Operational2, Efficiency2)
hold on;
plot(Operational2, Carnot2)
title('Efficiency and Carnot Efficiency vs. Run Number for inventor 2')
xlabel('Run Number')
ylabel('Efficiency')
legend('Efficiency','Carnot Efficiency')
axis([1,10,0,0.5])

% wrties all the efficiencies and carnot efficiencies to an xlsx file
writematrix(Efficiency1(1,:), 'efficiencies.xlsx', 'Range', 'A1:J1');
writematrix(Carnot1(1,:), 'efficiencies.xlsx', 'Range', 'A2:J2');

writematrix(Efficiency2(1,:), 'efficiencies.xlsx', 'Range', 'A4:J4');
writematrix(Carnot2(1,:), 'efficiencies.xlsx', 'Range', 'A5:J5');

% checks the matrices to see who has an efficiency value higher
% than the carnot efficiency
for j = 1:10
    if Efficiency1(j) > Carnot1(j)
        disp('inventor 2 is correct')
        break
    end

    if Efficiency2(j) > Carnot2(j)
        disp('inventor 1 is correct')
        break
    end
end

% closes the files
fclose(fid);
fclose(Fid);