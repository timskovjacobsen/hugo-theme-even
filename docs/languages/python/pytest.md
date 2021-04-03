
# pytest

## Group tests with custom markers

Tests can be grouped by the `pytest.mark` decorator. This can be used on many ways, for example for pre-commit hooks.

```python
@pytest.mark.precommit
def test_func():
    pass
```

Run all hooks in the `precommit` group by

```python
pytest -m precommit
```
