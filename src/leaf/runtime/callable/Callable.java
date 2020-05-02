package leaf.runtime.callable;

import java.util.List;

import leaf.runtime.Engine;
import leaf.runtime.reference.Reference;

public interface Callable {
	public Reference call(Engine engine, List<Reference> arguments);
}
