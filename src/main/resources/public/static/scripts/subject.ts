type FontType = "LL" | "L" | "M" | "S" | "SS";

class Subject {
    public constructor(
        public readonly id: string,
        public readonly name: string,
        private readonly absence: number,
        private readonly maxAbsence: number,
        private readonly late: number,
    ) {}

    public get displayLate(): string {
        return `${this.late}/3`;
    }

    public get displayAbsence(): string {
        return `${this.absence}/${this.maxAbsence}`;
    }

    public get colorStyle(): { "background-color": string } {
        return { "background-color": this.color };
    }


    public get titleClasses(): string[] {
        return ["title", this.fontSize];
    }

    public transform = () => {
        window.location.href = "/register/" + this.id;
    }

    private get fontSize(): FontType {
        if (this.name.length <= 3) return "LL";
        if (this.name.length <= 5) return "L";
        if (this.name.length <= 7) return "M";
        if (this.name.length <= 9) return "S"
        return "SS";
    }

    private get color(): string {
        const per = this.absence / this.maxAbsence;
        if (per >= 0.9) return "rgba(234, 74, 74, 0.8)";
        if (per >= 0.8) return "rgba(234, 141, 74, 0.8)";
        if (per >= 0.7) return "rgba(234, 199, 74, 0.8)";
        if (per === 0) return "rgba(255, 255, 255, 0.8)";
        return "rgba(166, 254, 249, 0.8)"
    }
}