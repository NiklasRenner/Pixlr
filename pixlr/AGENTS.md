# Pixlr Agent Notes

## Project Shape

Pixlr is a small Java/Swing platformer prototype. The runtime entry point is
`dk.renner.pixlr.game.Game`, which extends the custom `Frame` game window and
starts separate logic and rendering threads.

The level is image-driven: `Level` reads pixels from `graphics/levelTest.png`
and maps colors to `GameObject` implementations. Sprites are loaded from
classpath resources under `src/main/resources/graphics`.

## Tooling Decisions

Use the Maven Wrapper for all project commands:

```powershell
.\mvnw.cmd clean verify
.\mvnw.cmd package
```

The wrapper is pinned to Maven `3.9.16`, the latest Maven GA release verified
from Apache's release history on 2026-06-06. The build targets Java `25`.
This machine uses Eclipse Temurin JDK `25.0.3` installed at `C:\SDK\jdk-25`;
the user's `JAVA_HOME` and `Path` should point there.

## Testing Strategy

Keep tests focused on deterministic code that does not require opening a Swing
window. Good candidates are level color mapping, resource loading, sprite sheet
slicing, configuration defaults, collision math, and object state transitions.

Avoid starting `Game` or `Frame` in unit tests unless the test explicitly owns
window lifecycle and thread cleanup.

## Code Practices

Prefer interfaces at method boundaries, for example `List<GameObject>` instead
of `ArrayList<GameObject>`.

Resource loading should fail with clear exceptions. Returning `null` for
missing images tends to defer failures into rendering code, where the root cause
is harder to diagnose.

Keep refactors incremental. This project has tightly coupled rendering,
physics, and object state, so preserve behavior first and carve out testable
logic in small steps.
