# Python

## Object-Oriented Programming

### Abstract Base Class

An Abstract Base Class (or just Abstract Class) is a class that serves as a cookiecutter for for its
child classes. It defines abstract methods that the child classes should override.

An Abstract Base Class should not be instantiated directly by the user of the API.

### Abstract Methods

An Abstract Method is a method which is defined inside an abstract base class.
Generally, it has no implementation but instead encourages that child classes should
override it with a method specific to each particular child class.

An abstract method could be implemented as below.

```python
    class Animal():

        def move(self):
            raise NotImplementedError
```

If any child class of `Animal` refrains from overriding `move`, a call
to the method will throw `NotImplementedError`. 

This implementation has the consequences that

* Creating an instance of `Animal` will throw `NotImplementedError`  
* Creating an instance of *any* child class that has *not* overridden `move`
  will throw `NotImplementedError`


**A better way** of implementing an Abstract Method is via ``abc``: 

```python

    from abc import ABC, abstractmethod

    class Animal(ABC):
        '''This is an abstract class'''

        @abstractmethod
        def move(self):
            '''This is an abstract method'''
```

**This is often the desired behavior**, since it brings stricter requirements. First
of all, the primary points of the abstract base class is that the user of the API
should not create instances of it. That will be done only through the internals of
the program. Secondly, it enforces *all* child classes to implement a ``move`` method.

An attempt to instantiate an instance of `Animal` will throw `#!python TypeError:` `Can't
 instantiate abstract class Animal with abstract methods move`.

## Method Chaining

Method Chaining is a design method in OOP where methods modify and return the object
itself. The modified object can then be modified further by the next method in the
chain via dot-notation.  

Method Chaining can be implemented as shown in the example below:

```python
    # TODO! Create example
```

A good example of a simple library which uses Method Chaining is 
[schedule](https://schedule.readthedocs.io/en/stable/).

Whether to use Method Chaining or not is largely a design choice from the developer.
It can be a good tool in specific implementations where the chain can make the user
feel like he/she is writing pseudo code in the API calls.