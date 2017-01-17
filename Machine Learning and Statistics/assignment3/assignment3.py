import numpy as np
import scipy as sp
import scipy.optimize
import pandas as pd
import matplotlib as mpl
import sys
from sklearn import datasets
import math
import matplotlib.pyplot as plt
from sklearn.cross_validation import KFold



def logistic_func(th, x):
    return float(1) / (1 + math.e**(-x.dot(th)))

def log_grad(th, x, y):
    first = logistic_func(th, x) - np.squeeze(y)
    final = first.T.dot(x)
    return final

def costf(th, x, y):
    log_func = logistic_func(th,x)
    y = np.squeeze(y)
    step1 = y * np.log(log_func)
    step2 = (1-y) * np.log(1 - log_func) 
    step3 =  (1-y) * np.log(1 - log_func)
    final = -step1 - step2 - step3
    return np.mean(final)

def grad_descent(th_values, X, y, lr=.001, converge=.001):
    X = (X - np.mean(X, axis=0)) / np.std(X, axis=0)
    cost_iter = []
    cost = costf(th_values, X, y)
    cost_iter.append([0, cost])
    _cost = 1
    i = 1

    while(_cost > converge):
        old_cost = cost
        th_values = th_values - (lr * log_grad(th_values, X, y))
        cost = costf(th_values, X, y)
        cost_iter.append([i, cost])
        _cost = old_cost - cost
        i+=1
    return th_values, np.array(cost_iter)

def predict(th, X, hard=True):
    #normalize
    X = (X - np.mean(X, axis=0)) / np.std(X, axis=0)
    prob = logistic_func(th, X)
    prediction_value = np.where(prob >= .5, 1, 0)
    if hard:
        return prediction_value
    return prob

def evalf(arg, cons):
    return arg[0]*cons[0] + arg[1]*cons[1] + arg[2]*cons[2] + arg[3]*cons[3]

# prepare the data
data = pd.read_csv('C:/Users/user/Downloads/owls15.csv')
data.loc[data["type"] == "LongEaredOwl", "type"] = 0
data.loc[data["type"] == "SnowyOwl", "type"] = 1
data.loc[data["type"] == "BarnOwl", "type"] = 2

bodyLength = [];
wingLength =[];
bodywidth = [];
wingWidth = [];
type=[];

for d in data["body-length"]:
    bodyLength.append(d)

for d in data["wing-length"]:
    wingLength.append(d)

for d in data["body-width"]:
    bodywidth.append(d)

for d in data["wing-width"]:
    wingWidth.append(d)

for d in data["type"]:
    type.append(d)

combinedClassArray = np.array([bodyLength,wingLength,bodywidth,wingWidth])

X = combinedClassArray.T
y = type

#getting total count of objects
longEarCount = data.loc[data["type"] == 0, "type"].count()
SnowyCount = data.loc[data["type"] == 1, "type"].count()
BarnCount = data.loc[data["type"] == 1, "type"].count()

totalCount = longEarCount+SnowyCount+BarnCount
p = longEarCount+SnowyCount

#splitting data into training and testing data 
train = X[:(totalCount*(2/3))]
test = X[(totalCount*(2/3)):]

#calculating training count
trainCount = totalCount*(2/3)
testCount = totalCount - trainCount
shape = train.shape[1]

y_flip = np.logical_not(y[:int(trainCount)]) #0 becomes true , eveything else is false
betas = np.zeros(shape)
fitted_values, cost_iter = grad_descent(betas, train, y_flip)
print(fitted_values)
plt.show()

predicted = predict(fitted_values, test)
invert_y_test = np.logical_not(y[int(trainCount):])
print(testCount)
print("predicted values =" + str(predicted))
print(" Number of correct predictions: " + str(np.sum(invert_y_test == predicted)))
print("Percentage correct : " + str(np.sum(invert_y_test == predicted)/testCount))